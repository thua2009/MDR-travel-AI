# MDR Travel AI – Intelligent Da Nang Travel Companion

An interactive, multilingual AI travel assistant for Da Nang, Vietnam, powered by Google Gemini, featuring dynamic weather-aware re-routing, dual-persona adaptation (Solo Explorer vs. Multi-Generational Family Safe), and an Anti-Tourist-Trap Menu Scanner.

## User Review & Critical Decisions

> [!IMPORTANT]
> The following architectural decisions were aligned during Phase 1 clarification:

- **Confirmed: Adaptive Multi-Panel Layout**: The Android app uses an adaptive layout that renders a bottom navigation tab bar on handheld phones (switching between Co-Pilot, Itinerary Timeline, and Menu Scanner / Route) and seamlessly expands into a 3-column panoramic dashboard on tablets, foldables, or wide landscape viewports.
- **Confirmed: Gemini AI with Smart Fallback Engine**: Live Gemini API integration with instant local domain knowledge fallbacks ensures uninterrupted hackathon/demo performance even under network latency or missing API keys.
- **Confirmed: Authentic Da Nang Seafood Preset**: The Anti-Ripoff Scanner comes preloaded with authentic coastal Da Nang seafood menu items (King Crab, Steamed Squid, Coconut Prawns, Clams, spicy lemongrass snails) with price fairness meters and allergy/chili warnings.

---

## 1. Overview & Core Concept

**MDR Travel AI** solves two major pain points for tourists visiting Da Nang and Central Vietnam:
1. **Sudden Tropical Weather Disruptions**: Torrential rains at coastal or mountain attractions (Son Tra Peninsula, Hai Van Pass, Marble Mountains) trap travelers without actionable indoor alternatives.
2. **Tourism Price Gouging & Family Incompatibilities**: Overpriced seafood menus and hidden tourist traps, combined with steep, non-stroller-accessible stairs or excessively spicy local dishes that harm toddlers and elderly family members.

### Core Personas & Modes
- **🎒 Khách Tự Túc (Solo Explorer Mode)**: Prioritizes hidden alley street food (Chợ Cồn, Chợ Bắc Mỹ An), scenic coastal motorbike routes, photography viewpoints (Đỉnh Bàn Cờ, Bán đảo Sơn Trà), and budget-conscious adventures.
- **👨‍👩‍👧‍👦 Gia Đình Đa Thế Hệ (Family Safe Mode)**: Prioritizes wheelchair and stroller-friendly pathways, rest stops for seniors (70+ years old), non-spicy & allergen-aware dining for toddlers, and verified transparent pricing.

---

## 2. User Experience & Visual Design

### Multilingual Support (Default: Tiếng Việt)
The top navigation bar provides an interactive 4-language switcher:
- 🇻🇳 **Tiếng Việt** (Default system language & default prompt tone)
- 🇬🇧 **English**
- 🇰🇷 **한국어** (Korean tourists are Da Nang's largest international demographic)
- 🇯🇵 **日本語**

Switching the language instantly updates all top navigation controls, timeline destination cards, smart badges, weather alert dialogs, scanner feedback meters, and AI prompt contexts.

### Visual Identity & Theme
- **Theme Direction**: Vibrant Coastal Elegance (Da Nang Ocean Cyan `#007791`, Warm Lantern Amber `#F59E0B`, Dragon Bridge Gold `#EAB308`, Deep Slate `#0F172A`, Crisp Cloud White `#F8FAFC`).
- **Typography**: Clean, readable sans typography with high legibility for Vietnamese diacritics, Korean Hangul, and Japanese Kanji.
- **Surface Depth**: M3 Elevated Cards, semi-transparent frosted badges, soft borders, and vivid status indicators (Emerald for local fair price, Amber for weather alerts, Crimson for tourist trap warnings).

### Interactive User Flows
1. **Persona Switching Flow**: Toggling between Solo and Family dynamically updates the center timeline with specialized destinations, budget estimates, and accessibility badges.
2. **Weather Re-routing Flow**: Toggling the Live Weather simulator from "☀️ Nắng đẹp (28°C)" to "🌧️ Mưa lớn bất chợt" immediately triggers an Amber Alert banner. Tapping `[⚡ Cập nhật lịch trình ngay (Auto-Reroute)]` swaps outdoor afternoon spots (Son Tra / Hai Van) for covered cultural sanctuaries (Cham Sculpture Museum & Pottery Workshop).
3. **Menu Scanner & Anti-Ripoff Audit**: The user taps "Dùng ảnh Menu mẫu" or selects an image. The scanner displays translated dishes, checks current market prices (e.g., Cua Huỳnh Đế 650,000 VND vs 1,800,000 VND tourist trap), and flags allergens/spices for Family Mode.
4. **Interactive Co-Pilot Chat**: Features quick chip suggestions and multi-turn conversational responses tailored to Da Nang local knowledge and current persona/weather context.

---

## 3. Key Product Decisions & Trade-Offs

- **Decision 1: Dual-Mode Architecture (Gemini API + Instant Offline Fallback)**
  - *Chosen Approach*: The repository implements a unified AI Service that queries the Gemini API while instantly falling back to an authentic, curated Da Nang knowledge engine if network delays occur or credentials are not yet configured in AI Studio.
  - *Why*: Eliminates hackathon demo risk and guarantees 100% smooth evaluation without blank screens.
- **Decision 2: Comprehensive In-App Localization Engine**
  - *Chosen Approach*: Centralized `MdrLocalization` dictionary maintaining synchronized translations for all 4 languages (VI, EN, KO, JA) for UI labels, badges, alert texts, and menu items.
  - *Why*: Eliminates latency of round-tripping static UI text through network calls while ensuring authentic terminology (e.g., "Chống chặt chém", "Chuẩn giá bản địa", "바가지 방지", "ぼったくり防止").
- **Decision 3: Adaptive Layout with Compose Window Size Classes**
  - *Chosen Approach*: Auto-detects screen width (`BoxWithConstraints`). If width > 720dp, renders a 3-column panoramic dashboard; on standard mobile devices, provides a bottom navigation bar with badged tabs.
  - *Why*: Delivers an optimal native mobile experience while honoring the multi-column dashboard specification on tablets and web preview viewports.

---

## 4. Technical Architecture & Data Strategy

```
┌────────────────────────────────────────────────────────────────────────┐
│                          Top Navigation Bar                            │
│  [Logo: MDR Travel AI]  [Persona Toggle]  [Weather Sim]  [Lang VI/EN/KO/JA]│
└────────────────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
       ┌────────────────────────────┼───────────────────────────┐
       ▼                            ▼                           ▼
┌──────────────┐             ┌──────────────┐            ┌──────────────┐
│  Left Panel  │             │ Center Panel │            │ Right Panel  │
│  AI Co-Pilot │             │  Itinerary   │            │ Menu Scanner │
│  & Weather   │             │   Timeline   │            │   & Routes   │
│    Alert     │             │              │            │   Summary    │
└──────────────┘             └──────────────┘            └──────────────┘
       │                            │                           │
       └────────────────────────────┼───────────────────────────┘
                                    ▼
       ┌────────────────────────────────────────────────────────┐
       │                  MdrTravelViewModel                    │
       │   - personaState: Solo / Family                        │
       │   - weatherState: Sunny / Rainy                        │
       │   - languageState: VI / EN / KO / JA                   │
       │   - itineraryList: StateFlow<List<DestinationItem>>    │
       │   - chatMessages: StateFlow<List<ChatMessage>>         │
       │   - menuScanResult: StateFlow<MenuScanResult?>         │
       │   - routeSummary: StateFlow<RouteSummary>              │
       └────────────────────────────────────────────────────────┘
                                    │
                    ┌───────────────┴───────────────┐
                    ▼                               ▼
       ┌─────────────────────────┐     ┌─────────────────────────┐
       │   GeminiAiRepository    │     │   Da Nang Local Store   │
       │ (Firebase AI / Gemini)  │     │  (Prices, Routes, POIs) │
       └─────────────────────────┘     └─────────────────────────┘
```

### State & Interactive Component Mapping
- **`personaMode`**: Mutates active itinerary recommendations, chat system prompt context, and allergy warning priorities.
- **`weatherState`**: Controls the ambient weather banner, triggers the Amber Alert when set to Rainy, and calculates alternative indoor venues.
- **`itineraryState`**: Reactive list of timeline cards with verified cost, travel time, and 4 smart badge categories (`Verified Local Price`, `Senior/Stroller Friendly`, `Kid-Safe Non-Spicy`, `Indoor Weather-Safe`).
- **`menuScanState`**: Contains parsed items, price fairness score (0–100%), fair price status (`Chuẩn giá bản địa` vs `Cảnh báo giá cao`), and family safety flags.
- **`routeSummaryState`**: Calculates simulated total distance (km), travel time (mins), and estimated transparent Grab/Taxi fare in VND.
