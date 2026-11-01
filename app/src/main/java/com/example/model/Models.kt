package com.example.model

enum class Language(
    val code: String,
    val flag: String,
    val displayName: String,
    val shortLabel: String
) {
    VI("vi", "🇻🇳", "Tiếng Việt", "VI"),
    EN("en", "🇬🇧", "English", "EN"),
    KO("ko", "🇰🇷", "한국어", "KO"),
    JA("ja", "🇯🇵", "日本語", "JA");

    companion object {
        val DEFAULT = VI
    }
}

enum class PersonaMode {
    SOLO,   // Khách Tự Túc (Solo Explorer)
    FAMILY  // Gia Đình Đa Thế Hệ (Family Safe Mode)
}

enum class WeatherState {
    SUNNY,  // Nắng đẹp 28°C
    RAINY   // Mưa lớn bất chợt (Sudden Heavy Rain Alert)
}

enum class SmartBadgeType {
    VERIFIED_LOCAL_PRICE,     // ✅ Giá chuẩn bản địa
    SENIOR_STROLLER_FRIENDLY, // ♿ Phù hợp người già & xe đẩy
    KID_SAFE_NON_SPICY,       // 🌶️ Không cay - An toàn cho bé
    INDOOR_WEATHER_SAFE       // 🏠 Trong nhà - Tránh mưa
}

data class DestinationItem(
    val id: String,
    val time: String,
    val nameVi: String,
    val nameEn: String,
    val nameKo: String,
    val nameJa: String,
    val descriptionVi: String,
    val descriptionEn: String,
    val descriptionKo: String,
    val descriptionJa: String,
    val estimatedCostVnd: Long,
    val travelTimeMinutes: Int,
    val badges: List<SmartBadgeType>,
    val locationArea: String,
    val isIndoor: Boolean = false,
    val isReroutedAlternate: Boolean = false
) {
    fun localizedName(lang: Language): String = when (lang) {
        Language.VI -> nameVi
        Language.EN -> nameEn
        Language.KO -> nameKo
        Language.JA -> nameJa
    }

    fun localizedDescription(lang: Language): String = when (lang) {
        Language.VI -> descriptionVi
        Language.EN -> descriptionEn
        Language.KO -> descriptionKo
        Language.JA -> descriptionJa
    }
}

enum class Sender {
    USER,
    AI
}

data class ChatMessage(
    val id: String,
    val sender: Sender,
    val text: String,
    val timestamp: String,
    val isAutoRerouteSuggested: Boolean = false
)

enum class PriceStatus {
    FAIR_PRICE,        // Chuẩn giá bản địa (Green meter)
    TOURIST_TRAP_HIGH  // Cảnh báo giá cao (Red meter)
}

data class ScannedDishItem(
    val id: String,
    val nameVi: String,
    val nameEn: String,
    val nameKo: String,
    val nameJa: String,
    val priceVnd: Long,
    val benchmarkVnd: Long,
    val priceStatus: PriceStatus,
    val isSpicy: Boolean,
    val allergenNoteVi: String?,
    val allergenNoteEn: String?,
    val allergenNoteKo: String?,
    val allergenNoteJa: String?
) {
    fun localizedName(lang: Language): String = when (lang) {
        Language.VI -> nameVi
        Language.EN -> nameEn
        Language.KO -> nameKo
        Language.JA -> nameJa
    }

    fun localizedAllergen(lang: Language): String? = when (lang) {
        Language.VI -> allergenNoteVi
        Language.EN -> allergenNoteEn
        Language.KO -> allergenNoteKo
        Language.JA -> allergenNoteJa
    }
}

data class MenuScanResult(
    val restaurantName: String,
    val address: String,
    val fairnessScorePercent: Int, // 0 - 100
    val fairnessVerdictVi: String,
    val fairnessVerdictEn: String,
    val fairnessVerdictKo: String,
    val fairnessVerdictJa: String,
    val dishes: List<ScannedDishItem>,
    val scannedImageRes: Int
)

data class RouteSummary(
    val totalDistanceKm: Double,
    val totalTravelTimeMinutes: Int,
    val estimatedGrabTaxiVnd: Long,
    val stopsCount: Int,
    val isPriceGuaranteed: Boolean = true
)
