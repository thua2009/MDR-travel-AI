package com.example

import com.example.data.DaNangDataStore
import com.example.localization.MdrLocalization
import com.example.model.Language
import com.example.model.PersonaMode
import com.example.model.PriceStatus
import com.example.model.WeatherState
import com.example.viewmodel.MdrTravelViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun `test multilingual strings for all supported languages`() {
        val vi = MdrLocalization.get(Language.VI)
        assertEquals("MDR Travel AI", vi.appTitle)
        assertTrue(vi.soloModeLabel.contains("Khách Tự Túc"))

        val en = MdrLocalization.get(Language.EN)
        assertTrue(en.soloModeLabel.contains("Solo Explorer"))

        val ko = MdrLocalization.get(Language.KO)
        assertTrue(ko.soloModeLabel.contains("나홀로 탐험가"))

        val ja = MdrLocalization.get(Language.JA)
        assertTrue(ja.soloModeLabel.contains("一人旅エクスプローラー"))
    }

    @Test
    fun `test persona mode switching between solo and family`() {
        val viewModel = MdrTravelViewModel()
        assertEquals(PersonaMode.SOLO, viewModel.currentPersona.value)
        assertEquals(6, viewModel.itinerary.value.size)

        viewModel.setPersona(PersonaMode.FAMILY)
        assertEquals(PersonaMode.FAMILY, viewModel.currentPersona.value)
        assertEquals(6, viewModel.itinerary.value.size)
        // Verify family items contain senior/stroller badges
        val hasFamilyItem = viewModel.itinerary.value.any { it.nameVi.contains("Mì Quảng 1A") }
        assertTrue(hasFamilyItem)
    }

    @Test
    fun `test weather alert and auto reroute`() {
        val viewModel = MdrTravelViewModel()
        viewModel.setWeather(WeatherState.RAINY)
        assertEquals(WeatherState.RAINY, viewModel.currentWeather.value)
        assertFalse(viewModel.isRerouted.value)

        // Trigger Auto Reroute
        viewModel.triggerAutoReroute()
        assertTrue(viewModel.isRerouted.value)

        // Verify indoor items were inserted
        val hasIndoorChamMuseum = viewModel.itinerary.value.any { it.nameVi.contains("Bảo Tàng Điêu Khắc Chăm") }
        assertTrue(hasIndoorChamMuseum)
    }

    @Test
    fun `test seafood menu scanner data integrity`() {
        val sample = DaNangDataStore.sampleSeafoodMenuResult
        assertNotNull(sample)
        assertTrue(sample.dishes.isNotEmpty())
        assertEquals(95, sample.fairnessScorePercent)

        // Verify King crab is fair price
        val crab = sample.dishes.first { it.id == "dish_1" }
        assertEquals(PriceStatus.FAIR_PRICE, crab.priceStatus)
        assertEquals(650_000L, crab.priceVnd)

        // Verify spicy snail is flagged as high price warning and spicy
        val snail = sample.dishes.first { it.id == "dish_5" }
        assertEquals(PriceStatus.TOURIST_TRAP_HIGH, snail.priceStatus)
        assertTrue(snail.isSpicy)
    }
}
