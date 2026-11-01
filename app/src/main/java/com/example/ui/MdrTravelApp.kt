package com.example.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.localization.MdrLocalization
import com.example.model.WeatherState
import com.example.ui.components.AiCoPilotPanel
import com.example.ui.components.ItineraryTimelinePanel
import com.example.ui.components.MenuScannerPanel
import com.example.ui.components.TopNavigationBar
import com.example.viewmodel.MdrTravelViewModel

@Composable
fun MdrTravelApp(
    viewModel: MdrTravelViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val currentPersona by viewModel.currentPersona.collectAsState()
    val currentWeather by viewModel.currentWeather.collectAsState()
    val isRerouted by viewModel.isRerouted.collectAsState()
    val itinerary by viewModel.itinerary.collectAsState()
    val chatMessages by viewModel.chatMessages.collectAsState()
    val isAiThinking by viewModel.isAiThinking.collectAsState()
    val menuScanResult by viewModel.menuScanResult.collectAsState()
    val isScanningMenu by viewModel.isScanningMenu.collectAsState()
    val routeSummary by viewModel.routeSummary.collectAsState()
    val activeTab by viewModel.activeTab.collectAsState()

    val strings = MdrLocalization.get(currentLanguage)

    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Navigation Bar
            TopNavigationBar(
                currentLanguage = currentLanguage,
                currentPersona = currentPersona,
                currentWeather = currentWeather,
                onLanguageChange = { viewModel.setLanguage(it) },
                onPersonaChange = { viewModel.setPersona(it) },
                onWeatherToggle = {
                    val nextWeather = if (currentWeather == WeatherState.SUNNY) WeatherState.RAINY else WeatherState.SUNNY
                    viewModel.setWeather(nextWeather)
                }
            )

            // Responsive Content: 3 Columns on Tablet/Desktop, Tabbed View on Phones
            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                val isWideScreen = maxWidth >= 840.dp

                if (isWideScreen) {
                    // 3-Column Panoramic Dashboard
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                            .testTag("panoramic_dashboard_row")
                    ) {
                        // Column 1 (Left): AI Co-Pilot & Dynamic Alert
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                        ) {
                            AiCoPilotPanel(
                                chatMessages = chatMessages,
                                isAiThinking = isAiThinking,
                                currentWeather = currentWeather,
                                isRerouted = isRerouted,
                                currentLanguage = currentLanguage,
                                onSendMessage = { viewModel.sendMessage(it) },
                                onAutoRerouteClick = { viewModel.triggerAutoReroute() }
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Column 2 (Center): Timeline Itinerary
                        Box(
                            modifier = Modifier
                                .weight(1.3f)
                                .fillMaxSize()
                        ) {
                            ItineraryTimelinePanel(
                                itinerary = itinerary,
                                currentLanguage = currentLanguage,
                                currentPersona = currentPersona,
                                isRerouted = isRerouted
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Column 3 (Right): Menu Scanner & Route Overview
                        Box(
                            modifier = Modifier
                                .weight(1.1f)
                                .fillMaxSize()
                        ) {
                            MenuScannerPanel(
                                menuScanResult = menuScanResult,
                                isScanning = isScanningMenu,
                                routeSummary = routeSummary,
                                currentLanguage = currentLanguage,
                                onLoadSampleMenu = { viewModel.loadSampleMenu() }
                            )
                        }
                    }
                } else {
                    // Handheld Phone: Animated Switcher between the 3 views
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            AnimatedContent(
                                targetState = activeTab,
                                transitionSpec = { fadeIn() togetherWith fadeOut() },
                                label = "panelSwitch"
                            ) { tab ->
                                when (tab) {
                                    0 -> AiCoPilotPanel(
                                        chatMessages = chatMessages,
                                        isAiThinking = isAiThinking,
                                        currentWeather = currentWeather,
                                        isRerouted = isRerouted,
                                        currentLanguage = currentLanguage,
                                        onSendMessage = { viewModel.sendMessage(it) },
                                        onAutoRerouteClick = { viewModel.triggerAutoReroute() }
                                    )
                                    1 -> ItineraryTimelinePanel(
                                        itinerary = itinerary,
                                        currentLanguage = currentLanguage,
                                        currentPersona = currentPersona,
                                        isRerouted = isRerouted
                                    )
                                    2 -> MenuScannerPanel(
                                        menuScanResult = menuScanResult,
                                        isScanning = isScanningMenu,
                                        routeSummary = routeSummary,
                                        currentLanguage = currentLanguage,
                                        onLoadSampleMenu = { viewModel.loadSampleMenu() }
                                    )
                                }
                            }
                        }

                        // Bottom Navigation Bar
                        NavigationBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("bottom_nav_bar"),
                            tonalElevation = 6.dp
                        ) {
                            NavigationBarItem(
                                selected = activeTab == 0,
                                onClick = { viewModel.setActiveTab(0) },
                                modifier = Modifier.testTag("nav_tab_copilot"),
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (currentWeather == WeatherState.RAINY && !isRerouted) {
                                                Badge(
                                                    containerColor = Color(0xFFF59E0B),
                                                    modifier = Modifier.size(8.dp)
                                                )
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AutoAwesome,
                                            contentDescription = strings.tabCopilot
                                        )
                                    }
                                },
                                label = {
                                    Text(
                                        text = strings.tabCopilot,
                                        fontWeight = if (activeTab == 0) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            )

                            NavigationBarItem(
                                selected = activeTab == 1,
                                onClick = { viewModel.setActiveTab(1) },
                                modifier = Modifier.testTag("nav_tab_itinerary"),
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            Badge(containerColor = MaterialTheme.colorScheme.primary) {
                                                Text("${itinerary.size}")
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CalendarMonth,
                                            contentDescription = strings.tabItinerary
                                        )
                                    }
                                },
                                label = {
                                    Text(
                                        text = strings.tabItinerary,
                                        fontWeight = if (activeTab == 1) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            )

                            NavigationBarItem(
                                selected = activeTab == 2,
                                onClick = { viewModel.setActiveTab(2) },
                                modifier = Modifier.testTag("nav_tab_menu_scanner"),
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (menuScanResult != null) {
                                                Badge(containerColor = Color(0xFF10B981)) {
                                                    Text("95")
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ReceiptLong,
                                            contentDescription = strings.tabMenuScanner
                                        )
                                    }
                                },
                                label = {
                                    Text(
                                        text = strings.tabMenuScanner,
                                        fontWeight = if (activeTab == 2) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
