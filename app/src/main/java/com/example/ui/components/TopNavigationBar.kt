package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.MdrLocalization
import com.example.model.Language
import com.example.model.PersonaMode
import com.example.model.WeatherState
import com.example.ui.theme.LanternAmber
import com.example.ui.theme.OceanBluePrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopNavigationBar(
    currentLanguage: Language,
    currentPersona: PersonaMode,
    currentWeather: WeatherState,
    onLanguageChange: (Language) -> Unit,
    onPersonaChange: (PersonaMode) -> Unit,
    onWeatherToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = MdrLocalization.get(currentLanguage)
    var isLangMenuExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("top_navigation_bar"),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            // First Row: App Branding + Cloud Run Badge + Language Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Logo & Cloud Badge
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(OceanBluePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🧭", fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = strings.appTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = strings.poweredByBadge,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                // Language Dropdown
                Box {
                    Row(
                        modifier = Modifier
                            .testTag("language_selector")
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                            .clickable { isLangMenuExpanded = true }
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Language",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${currentLanguage.flag} ${currentLanguage.shortLabel}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Open",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = isLangMenuExpanded,
                        onDismissRequest = { isLangMenuExpanded = false }
                    ) {
                        Language.values().forEach { lang ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(lang.flag, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            lang.displayName,
                                            fontWeight = if (lang == currentLanguage) FontWeight.Bold else FontWeight.Normal,
                                            color = if (lang == currentLanguage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                },
                                onClick = {
                                    onLanguageChange(lang)
                                    isLangMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Second Row: Dual Persona Mode Toggle + Live Weather Simulator Toggle
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Persona Toggle (Solo vs Family)
                Row(
                    modifier = Modifier
                        .testTag("persona_toggle_group")
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
                        .padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val isSolo = currentPersona == PersonaMode.SOLO
                    val soloBg by animateColorAsState(
                        targetValue = if (isSolo) OceanBluePrimary else Color.Transparent,
                        label = "soloBg"
                    )
                    val soloText = if (isSolo) Color.White else MaterialTheme.colorScheme.onSurfaceVariant

                    Row(
                        modifier = Modifier
                            .testTag("btn_mode_solo")
                            .clip(RoundedCornerShape(7.dp))
                            .background(soloBg)
                            .clickable { onPersonaChange(PersonaMode.SOLO) }
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Hiking,
                            contentDescription = "Solo Explorer",
                            tint = soloText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = strings.soloModeLabel,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSolo) FontWeight.Bold else FontWeight.Normal,
                            color = soloText
                        )
                    }

                    val isFamily = currentPersona == PersonaMode.FAMILY
                    val familyBg by animateColorAsState(
                        targetValue = if (isFamily) LanternAmber else Color.Transparent,
                        label = "famBg"
                    )
                    val familyText = if (isFamily) Color.White else MaterialTheme.colorScheme.onSurfaceVariant

                    Row(
                        modifier = Modifier
                            .testTag("btn_mode_family")
                            .clip(RoundedCornerShape(7.dp))
                            .background(familyBg)
                            .clickable { onPersonaChange(PersonaMode.FAMILY) }
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.FamilyRestroom,
                            contentDescription = "Family Safe Mode",
                            tint = familyText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = strings.familyModeLabel,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isFamily) FontWeight.Bold else FontWeight.Normal,
                            color = familyText
                        )
                    }
                }

                // Weather Simulator Toggle Button
                val isRainy = currentWeather == WeatherState.RAINY
                val weatherBorderColor = if (isRainy) Color(0xFFEF4444) else Color(0xFFEAB308)
                val weatherBg = if (isRainy) Color(0xFFFEF2F2) else Color(0xFFFFFBEB)

                Row(
                    modifier = Modifier
                        .testTag("weather_simulator_toggle")
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.5.dp, weatherBorderColor, RoundedCornerShape(10.dp))
                        .background(weatherBg)
                        .clickable { onWeatherToggle() }
                        .padding(horizontal = 10.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (isRainy) Icons.Default.Thunderstorm else Icons.Default.WbSunny,
                        contentDescription = "Weather",
                        tint = if (isRainy) Color(0xFFDC2626) else Color(0xFFD97706),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isRainy) strings.weatherRainy else strings.weatherSunny,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isRainy) Color(0xFF991B1B) else Color(0xFF92400E)
                    )
                }
            }
        }
    }
}
