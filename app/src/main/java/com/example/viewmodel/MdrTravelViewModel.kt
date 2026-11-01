package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DaNangDataStore
import com.example.model.ChatMessage
import com.example.model.DestinationItem
import com.example.model.Language
import com.example.model.MenuScanResult
import com.example.model.PersonaMode
import com.example.model.RouteSummary
import com.example.model.Sender
import com.example.model.WeatherState
import com.example.service.GeminiAiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MdrTravelViewModel(
    private val aiService: GeminiAiService = GeminiAiService()
) : ViewModel() {

    private val _currentLanguage = MutableStateFlow(Language.DEFAULT)
    val currentLanguage: StateFlow<Language> = _currentLanguage.asStateFlow()

    private val _currentPersona = MutableStateFlow(PersonaMode.SOLO)
    val currentPersona: StateFlow<PersonaMode> = _currentPersona.asStateFlow()

    private val _currentWeather = MutableStateFlow(WeatherState.SUNNY)
    val currentWeather: StateFlow<WeatherState> = _currentWeather.asStateFlow()

    private val _isRerouted = MutableStateFlow(false)
    val isRerouted: StateFlow<Boolean> = _isRerouted.asStateFlow()

    private val _itinerary = MutableStateFlow<List<DestinationItem>>(DaNangDataStore.soloItinerary)
    val itinerary: StateFlow<List<DestinationItem>> = _itinerary.asStateFlow()

    private val _routeSummary = MutableStateFlow(DaNangDataStore.calculateRouteSummary(DaNangDataStore.soloItinerary))
    val routeSummary: StateFlow<RouteSummary> = _routeSummary.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isAiThinking = MutableStateFlow(false)
    val isAiThinking: StateFlow<Boolean> = _isAiThinking.asStateFlow()

    private val _menuScanResult = MutableStateFlow<MenuScanResult?>(null)
    val menuScanResult: StateFlow<MenuScanResult?> = _menuScanResult.asStateFlow()

    private val _isScanningMenu = MutableStateFlow(false)
    val isScanningMenu: StateFlow<Boolean> = _isScanningMenu.asStateFlow()

    private val _activeTab = MutableStateFlow(0) // 0: CoPilot, 1: Timeline, 2: Scanner
    val activeTab: StateFlow<Int> = _activeTab.asStateFlow()

    private val _showRerouteBanner = MutableStateFlow(false)
    val showRerouteBanner: StateFlow<Boolean> = _showRerouteBanner.asStateFlow()

    init {
        // Initialize welcome message in Vietnamese
        addWelcomeMessage(Language.VI)
    }

    private fun addWelcomeMessage(language: Language) {
        val welcomeText = when (language) {
            Language.VI -> "Xin chào! Tôi là Trợ Lý Du Lịch Thông Minh MDR Travel AI tại Đà Nẵng. Tôi có thể hỗ trợ bạn lên lịch trình, quét menu chống chặt chém và tự động đổi hướng khi thời tiết thay đổi. Bạn đang cần hỗ trợ gì hôm nay?"
            Language.EN -> "Hello! I am your MDR Travel AI assistant for Da Nang. I can optimize your itinerary, scan menus to prevent tourist traps, and auto-reroute for sudden rain. How can I help you today?"
            Language.KO -> "안녕하세요! 다낭 전문 지능형 여행 비서 MDR Travel AI입니다. 맞춤 일정 안내, 바가지 방지 메뉴판 스캔, 우천 시 실내 코스 자동 변경을 도와드립니다. 무엇을 도와드릴까요?"
            Language.JA -> "こんにちは！ダナン特化型AIトラベルアシスタント「MDR Travel AI」です。日程の最適化、メニューのぼったくり検証、雨天時の屋内ルート再設定をサポートします。ご質問をどうぞ！"
        }
        _chatMessages.value = listOf(
            ChatMessage(
                id = "welcome",
                sender = Sender.AI,
                text = welcomeText,
                timestamp = currentTimestamp()
            )
        )
    }

    fun setLanguage(language: Language) {
        if (_currentLanguage.value != language) {
            _currentLanguage.value = language
            addWelcomeMessage(language)
        }
    }

    fun setPersona(persona: PersonaMode) {
        _currentPersona.value = persona
        _isRerouted.value = false
        _showRerouteBanner.value = false
        updateItinerary()
    }

    fun setWeather(weather: WeatherState) {
        _currentWeather.value = weather
        if (weather == WeatherState.SUNNY) {
            _isRerouted.value = false
            _showRerouteBanner.value = false
            updateItinerary()
        }
    }

    fun triggerAutoReroute() {
        _isRerouted.value = true
        _showRerouteBanner.value = true
        updateItinerary()

        // Also add AI confirmation in chat
        val rerouteChatText = when (_currentLanguage.value) {
            Language.VI -> "⚡ Đã tự động cập nhật lịch trình! Do có mưa lớn tại bán đảo và đèo, các điểm ngoài trời buổi chiều đã được chuyển sang Bảo tàng Điêu khắc Chăm & Xưởng Gốm trong nhà (cách 10 phút)."
            Language.EN -> "⚡ Itinerary automatically updated! Due to heavy coastal rain, outdoor afternoon destinations have been safely rerouted to the indoor Cham Sculpture Museum & Pottery Workshop (10 mins away)."
            Language.KO -> "⚡ 일정 자동 변경 완료! 해안가 폭우로 인해 오후 야외 일정이 100% 실내 비가림 코스인 참 조각 박물관 및 전통 도자기 공방으로 안전하게 전환되었습니다."
            Language.JA -> "⚡ 日程の自動再設定が完了しました！激しい雨のため、午後の屋外予定を完全屋内のチャム彫刻博物館＆陶芸体験工房へ安全に変更しました。"
        }
        val currentList = _chatMessages.value.toMutableList()
        currentList.add(
            ChatMessage(
                id = "reroute_${System.currentTimeMillis()}",
                sender = Sender.AI,
                text = rerouteChatText,
                timestamp = currentTimestamp()
            )
        )
        _chatMessages.value = currentList
    }

    private fun updateItinerary() {
        val baseList = if (_currentPersona.value == PersonaMode.SOLO) {
            DaNangDataStore.soloItinerary
        } else {
            DaNangDataStore.familyItinerary
        }

        val finalList = if (_isRerouted.value && _currentWeather.value == WeatherState.RAINY) {
            // Replace the afternoon outdoor activities with sheltered indoor destinations
            val result = mutableListOf<DestinationItem>()
            for (item in baseList) {
                if (item.time.startsWith("15") || item.time.startsWith("16")) {
                    // Replaced by indoor alternates
                    continue
                }
                result.add(item)
            }
            // Insert the rainy indoor alternatives into the afternoon slot
            result.addAll(3, DaNangDataStore.rainyAfternoonReroute)
            result.sortedBy { it.time }
        } else {
            baseList
        }

        _itinerary.value = finalList
        _routeSummary.value = DaNangDataStore.calculateRouteSummary(finalList)
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return
        val userMsg = ChatMessage(
            id = "user_${System.currentTimeMillis()}",
            sender = Sender.USER,
            text = text.trim(),
            timestamp = currentTimestamp()
        )
        val updated = _chatMessages.value.toMutableList().apply { add(userMsg) }
        _chatMessages.value = updated
        _isAiThinking.value = true

        viewModelScope.launch {
            try {
                val reply = aiService.generateChatResponse(
                    prompt = text,
                    language = _currentLanguage.value,
                    persona = _currentPersona.value,
                    weather = _currentWeather.value
                )
                val aiMsg = ChatMessage(
                    id = "ai_${System.currentTimeMillis()}",
                    sender = Sender.AI,
                    text = reply,
                    timestamp = currentTimestamp(),
                    isAutoRerouteSuggested = _currentWeather.value == WeatherState.RAINY && !_isRerouted.value
                )
                _chatMessages.value = _chatMessages.value.toMutableList().apply { add(aiMsg) }
            } catch (e: Exception) {
                val errorMsg = ChatMessage(
                    id = "error_${System.currentTimeMillis()}",
                    sender = Sender.AI,
                    text = "Xin lỗi, đã xảy ra gián đoạn kết nối. Vui lòng thử lại sau giây lát.",
                    timestamp = currentTimestamp()
                )
                _chatMessages.value = _chatMessages.value.toMutableList().apply { add(errorMsg) }
            } finally {
                _isAiThinking.value = false
            }
        }
    }

    fun loadSampleMenu() {
        _isScanningMenu.value = true
        viewModelScope.launch {
            val result = aiService.analyzeMenu(
                isCustomImage = false,
                language = _currentLanguage.value,
                persona = _currentPersona.value
            )
            _menuScanResult.value = result
            _isScanningMenu.value = false
        }
    }

    fun clearMenuScan() {
        _menuScanResult.value = null
    }

    fun setActiveTab(tab: Int) {
        _activeTab.value = tab
    }

    private fun currentTimestamp(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }
}
