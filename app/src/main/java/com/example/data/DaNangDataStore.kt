package com.example.data

import com.example.R
import com.example.model.DestinationItem
import com.example.model.MenuScanResult
import com.example.model.PersonaMode
import com.example.model.PriceStatus
import com.example.model.RouteSummary
import com.example.model.ScannedDishItem
import com.example.model.SmartBadgeType
import com.example.model.WeatherState

object DaNangDataStore {

    val soloItinerary = listOf(
        DestinationItem(
            id = "solo_1",
            time = "08:30",
            nameVi = "Bún Chả Cá Bà Phiến & Cafe Vợt Hẻm Yên Bái",
            nameEn = "Ba Phien Fish Cake Noodle & Alley Filter Coffee",
            nameKo = "바피엔 어묵국수 & 옌바이 골목 수동 드립 커피",
            nameJa = "バー・フィエン魚すり身麺＆路地裏フィルター珈琲",
            descriptionVi = "Quán bún gia truyền hơn 30 năm, nước dùng thanh ngọt đậm đà, sau đó ghé cafe vợt hẻm hoài niệm.",
            descriptionEn = "Over 30-year authentic local fish cake noodle soup followed by retro alley filter coffee.",
            descriptionKo = "30년 전통의 깊고 시원한 로컬 어묵국수 후 골목길 전통 드리퍼 커피 체험.",
            descriptionJa = "30年以上の伝統を誇る魚すり身麺とレトロな路地裏ネルドリップ珈琲。",
            estimatedCostVnd = 55_000,
            travelTimeMinutes = 15,
            badges = listOf(SmartBadgeType.VERIFIED_LOCAL_PRICE),
            locationArea = "Quận Hải Châu"
        ),
        DestinationItem(
            id = "solo_2",
            time = "10:30",
            nameVi = "Chinh Phục Bán Đảo Sơn Trà & Đỉnh Bàn Cờ",
            nameEn = "Motorbike Ride: Son Tra Peninsula & Ban Co Peak",
            nameKo = "손짜 반도 바이크 라이딩 & 반꼬 봉우리 전망대",
            nameJa = "ソンチャ半島ツーリング＆バンコ頂上パノラマ展望",
            descriptionVi = "Cung đường uốn lượn ven biển tuyệt đẹp, ngắm voọc chà vá chân nâu và toàn cảnh Đà Nẵng từ trên cao.",
            descriptionEn = "Scenic coastal winding road, spot red-shanked douc langurs and panoramic Da Nang bay views.",
            descriptionKo = "아름다운 해안 바이크 코스, 희귀 야생 원숭이 및 다낭 해안 전경 조망.",
            descriptionJa = "息を呑む海岸ワインディングロード、貴重なアカアシドゥクラングールとダナン湾の絶景。",
            estimatedCostVnd = 80_000,
            travelTimeMinutes = 35,
            badges = listOf(SmartBadgeType.VERIFIED_LOCAL_PRICE),
            locationArea = "Bán đảo Sơn Trà"
        ),
        DestinationItem(
            id = "solo_3",
            time = "13:00",
            nameVi = "Phá Đảo Thiên Đường Ẩm Thực Chợ Cồn",
            nameEn = "Con Market Street Food Alley Feast",
            nameKo = "콘 시장 먹거리 골목 로컬 스트리트 푸드 정복",
            nameJa = "コン市場ローカルB級グルメ屋台街めぐり",
            descriptionVi = "Thiên đường đồ ăn vặt: bánh bèo chén tôm cháy, ram cuốn cải giòn rụm, ốc hút cay nồng chuẩn vị xứ Quảng.",
            descriptionEn = "Foodie haven: steamed rice cakes with dried shrimp, crispy spring rolls with mustard greens, spicy snails.",
            descriptionKo = "다낭 현지 미식의 성지: 반베오, 람꾸온까이 바삭 롤, 매콤한 옥훗 달팽이 요리.",
            descriptionJa = "ローカル屋台の殿堂：小皿米粉蒸しケーキ、からし菜生春巻き、ピリ辛巻き貝。",
            estimatedCostVnd = 95_000,
            travelTimeMinutes = 20,
            badges = listOf(SmartBadgeType.VERIFIED_LOCAL_PRICE),
            locationArea = "Chợ Cồn, Quận Hải Châu"
        ),
        DestinationItem(
            id = "solo_4",
            time = "15:30",
            nameVi = "Cung Đường Đèo Hải Vân & Làng Chài Nam Ô",
            nameEn = "Hai Van Pass Coastline & Nam O Fishing Village",
            nameKo = "하이반 고개 절경 라이딩 & 남오 전통 어촌마을",
            nameJa = "ハイヴァン峠ワインディング＆ナムオー漁村の海風",
            descriptionVi = "Đệ nhất hùng quan ngắm mây trời giao hòa với biển xanh, thưởng thức gỏi cá Nam Ô chuẩn vị truyền thống.",
            descriptionEn = "Vietnam's most dramatic mountain pass overlooking the East Sea, taste Nam O fish salad.",
            descriptionKo = "바다와 구름이 만나는 하이반 고개 파노라마, 남오 전통 생선 샐러드 맛보기.",
            descriptionJa = "雲と海が交差する名勝ハイヴァン峠とナムオー名物生魚ハーブ和え。",
            estimatedCostVnd = 70_000,
            travelTimeMinutes = 40,
            badges = listOf(SmartBadgeType.VERIFIED_LOCAL_PRICE),
            locationArea = "Quận Liên Chiểu"
        ),
        DestinationItem(
            id = "solo_5",
            time = "18:30",
            nameVi = "Hải Sản Bờ Kè Liên Chiểu – Giá Chuẩn Bản Địa",
            nameEn = "Lien Chieu Coastal Seafood – Honest Local Price",
            nameKo = "리엔찌에우 해안 해산물 포차 – 정직한 현지 단골가",
            nameJa = "リエンチウ海岸ローカル海鮮酒場 – 適正価格の宴",
            descriptionVi = "Quán bình dân sát biển Nguyễn Tất Thành, hải sản tươi sống vớt từ bể, cân đúng ký, không chặt chém.",
            descriptionEn = "Waterfront seafood street on Nguyen Tat Thanh with transparent live tanks and honest scales.",
            descriptionKo = "응우옌땃타인 해안가 정찰제 해산물 식당, 바가지 없는 신선한 조개와 생선 구이.",
            descriptionJa = "グエン・タット・タイン通りの明朗会計海鮮店、水槽から量り売り直送。",
            estimatedCostVnd = 240_000,
            travelTimeMinutes = 20,
            badges = listOf(SmartBadgeType.VERIFIED_LOCAL_PRICE),
            locationArea = "Bờ biển Liên Chiểu"
        ),
        DestinationItem(
            id = "solo_6",
            time = "21:00",
            nameVi = "Cầu Rồng Phun Lửa & Bia Craft Ven Sông Hàn",
            nameEn = "Dragon Bridge Fire Show & Han River Craft Beer",
            nameKo = "용다리 불쇼 관람 & 한강변 로컬 수제 맥주",
            nameJa = "ドラゴン橋火噴きショー＆ハン川クラフトビール",
            descriptionVi = "Chiêm ngưỡng rồng thép phun lửa và nước hoành tráng, hòa cùng không khí trẻ trung sôi động về đêm.",
            descriptionEn = "Witness the iconic dragon breathe fire and water, relaxing with local chilled riverside drinks.",
            descriptionKo = "주말 밤 불과 물을 뿜는 장엄한 용다리 쇼와 활기찬 한강 야경 즐기기.",
            descriptionJa = "大迫力のドラゴン橋ファイヤー＆ウォーターショーとハン川ナイトリバーサイド。",
            estimatedCostVnd = 90_000,
            travelTimeMinutes = 15,
            badges = listOf(SmartBadgeType.VERIFIED_LOCAL_PRICE),
            locationArea = "Bờ Đông Sông Hàn"
        )
    )

    val familyItinerary = listOf(
        DestinationItem(
            id = "fam_1",
            time = "08:30",
            nameVi = "Mì Quảng 1A Hải Phòng – Không Gian Bằng Phẳng",
            nameEn = "Mi Quang 1A Hai Phong – Senior & Stroller Friendly",
            nameKo = "미꽝 1A 하이퐁 – 계단 없는 완만한 가족 맛집",
            nameJa = "ミークアン 1A ハイフォン – バリアフリー名店",
            descriptionVi = "Món mì Quảng tôm thịt truyền thống, nước dùng thanh ngọt không cay cho bé, lối đi rộng rãi không bậc thang.",
            descriptionEn = "Mild, non-spicy traditional noodle bowls for toddlers, step-free access for grandma's comfort.",
            descriptionKo = "자극적이지 않고 담백한 전통 미꽝 국수, 유모차와 어르신을 위한 평평한 실내 동선.",
            descriptionJa = "辛くないマイルドな伝統ダナン麺、ベビーカーや車椅子も安心な段差なし設計。",
            estimatedCostVnd = 60_000,
            travelTimeMinutes = 15,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY,
                SmartBadgeType.KID_SAFE_NON_SPICY
            ),
            locationArea = "Quận Hải Châu"
        ),
        DestinationItem(
            id = "fam_2",
            time = "10:00",
            nameVi = "Dạo Mát Bãi Biển Mỹ Khê & Công Viên Biển Đông",
            nameEn = "My Khe Beach Promenade & East Sea Park Stroll",
            nameKo = "미케 비치 해변 산책로 & 동해 공원 비둘기 모이주기",
            nameJa = "ミーケービーチ遊歩道＆イーストシーパーク散策",
            descriptionVi = "Đường đi dạo rợp bóng dừa lót gạch nhẵn mịn, có ghế nghỉ cho người cao tuổi và đàn chim bồ câu thân thiện cho bé.",
            descriptionEn = "Smooth paved palm promenade with frequent rest benches for seniors and playful dove feeding for kids.",
            descriptionKo = "어르신 쉼터 벤치가 많은 야자수 평지 산책로, 아이들이 좋아하는 비둘기 먹이주기 체험.",
            descriptionJa = "シニア向けのベンチが充実した平坦な海沿いプロムナード、子供も喜ぶ鳩の広場。",
            estimatedCostVnd = 30_000,
            travelTimeMinutes = 15,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY
            ),
            locationArea = "Bãi biển Mỹ Khê"
        ),
        DestinationItem(
            id = "fam_3",
            time = "12:30",
            nameVi = "Cơm Niêu Gia Đình Trần Phú – Máy Lạnh Yên Tĩnh",
            nameEn = "Tran Phu Family Claypot Dining – Quiet AC Rooms",
            nameKo = "쩐푸 전통 뚝배기 가정식 – 조용한 에어컨 룸",
            nameJa = "チャンフー土鍋ご飯 – 静かな冷房個室ファミリーランチ",
            descriptionVi = "Cơm niêu thơm lừng, cá bống kho tộ, canh cua mồng tơi thanh mát, thực đơn dinh dưỡng dễ tiêu cho người lớn tuổi.",
            descriptionEn = "Traditional claypot rice, stewed fish, fresh crab soup, easy-to-digest nutrition for elders and toddlers.",
            descriptionKo = "구수한 뚝배기 밥과 부드러운 생선조림, 어르신 소화에 좋은 담백한 게살 채소국.",
            descriptionJa = "消化にやさしい土鍋ご飯、川魚の甘辛煮、滋養豊かな蟹出汁スープ。",
            estimatedCostVnd = 180_000,
            travelTimeMinutes = 15,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY,
                SmartBadgeType.KID_SAFE_NON_SPICY
            ),
            locationArea = "Quận Hải Châu"
        ),
        DestinationItem(
            id = "fam_4",
            time = "15:00",
            nameVi = "Ngũ Hành Sơn (Thang Máy Kính) & Làng Điêu Khắc Non Nước",
            nameEn = "Marble Mountains Glass Elevator & Non Nuoc Stone Village",
            nameKo = "오행산(마블 마운틴) 전망 엘리베이터 & 논느억 조각 마을",
            nameJa = "五行山シースルーエレベーター＆ノンヌォック石彫村",
            descriptionVi = "Hệ thống thang máy kính lên thẳng Linh Ứng Tự mà không phải leo bậc đá dốc, lối đi có tay vịn an toàn.",
            descriptionEn = "Glass panoramic elevator straight to Linh Ung Pagoda without steep stone stairs, safe handrails throughout.",
            descriptionKo = "가파른 돌계단 대신 통유리 엘리베이터로 편안하게 린응사 관람, 안전 손잡이 완비.",
            descriptionJa = "急な石段を登らずガラスエレベーターで直行、手すり完備で70代も安心。",
            estimatedCostVnd = 75_000,
            travelTimeMinutes = 25,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY
            ),
            locationArea = "Quận Ngũ Hành Sơn"
        ),
        DestinationItem(
            id = "fam_5",
            time = "18:00",
            nameVi = "Hải Sản Biển Rạng Liên Chiểu – Niêm Yết Giá & Món Không Cay",
            nameEn = "Bien Rang Seafood – Certified Transparent Pricing",
            nameKo = "비엔랑 로컬 해산물 – 100% 정찰제 & 순한맛 맞춤",
            nameJa = "ビエンラン海鮮レストラン – 100%明朗会計＆非辛メニュー",
            descriptionVi = "Bảng giá niêm yết rõ ràng, tôm sú hấp nước dừa thanh ngọt, cua hấp sả, cháo hải sản dinh dưỡng cho bé và người già.",
            descriptionEn = "Certified local price list: steamed coconut prawns, steamed crab, warm nutritious seafood porridge.",
            descriptionKo = "바가지 없는 투명한 가격표, 코코넛 새우찜, 어르신과 유아를 위한 영양 해물죽.",
            descriptionJa = "明朗価格表示、ココナッツ蒸し車海老、子供とシニアに優しい海鮮粥。",
            estimatedCostVnd = 280_000,
            travelTimeMinutes = 20,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY,
                SmartBadgeType.KID_SAFE_NON_SPICY
            ),
            locationArea = "Quận Liên Chiểu"
        ),
        DestinationItem(
            id = "fam_6",
            time = "20:30",
            nameVi = "Cầu Rồng & Dạo Thuyền Sông Hàn Ghế Ngồi Êm",
            nameEn = "Dragon Bridge View & Han River Gentle Cruise",
            nameKo = "용다리 야경 & 한강 유람선 안락한 지정 좌석 관람",
            nameJa = "ドラゴン橋＆ハン川遊覧船ゆったり指定席クルーズ",
            descriptionVi = "Du thuyền máy lạnh có áo phao trẻ em, ghế đệm êm cho người già ngắm trọn vẹn cảnh Đà Nẵng lung linh về đêm.",
            descriptionEn = "Comfortable cushioned riverboat seats, child life vests, breeze view of Dragon Bridge lights.",
            descriptionKo = "어린이 구명조끼 및 편안한 좌석 완비, 한강의 야경과 용다리 조명을 여유롭게 감상.",
            descriptionJa = "子供用ライフジャケットとクッション椅子完備、ハン川の夜景を優雅に周遊。",
            estimatedCostVnd = 120_000,
            travelTimeMinutes = 15,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY
            ),
            locationArea = "Bến tàu Sông Hàn"
        )
    )

    val rainyAfternoonReroute = listOf(
        DestinationItem(
            id = "rain_1",
            time = "15:00",
            nameVi = "Bảo Tàng Điêu Khắc Chăm Đà Nẵng (100% Mái Che)",
            nameEn = "Cham Sculpture Museum (100% Indoor Shelter)",
            nameKo = "다낭 참 조각 박물관 (100% 쾌적한 실내 코스)",
            nameJa = "ダナン・チャム彫刻博物館（完全屋内・雨天対応）",
            descriptionVi = "Không gian trưng bày di sản Champa vô giá, sàn gạch phẳng hoàn hảo cho xe đẩy, phòng trưng bày máy lạnh tránh mưa rào.",
            descriptionEn = "World-class Champa treasures, smooth flat flooring ideal for strollers/seniors, sheltered from heavy rain.",
            descriptionKo = "참파 왕국의 유물 보물창고, 유모차와 휠체어가 편안한 평지 실내 동선 및 쾌적한 냉방.",
            descriptionJa = "貴重なチャンパ彫刻を展示、ベビーカーや車椅子に優しい完全フラット構造の屋内。",
            estimatedCostVnd = 60_000,
            travelTimeMinutes = 10,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY,
                SmartBadgeType.INDOOR_WEATHER_SAFE
            ),
            locationArea = "Quận Hải Châu",
            isIndoor = true,
            isReroutedAlternate = true
        ),
        DestinationItem(
            id = "rain_2",
            time = "16:45",
            nameVi = "Xưởng Gốm & Làm Lồng Đèn Truyền Thống Trong Nhà",
            nameEn = "Indoor Pottery & Silk Lantern Craft Workshop",
            nameKo = "실내 전통 도자기 빚기 & 비단 등불 만들기 공방",
            nameJa = "伝統陶芸＆シルクランタン手作り屋内体験工房",
            descriptionVi = "Hoạt động trải nghiệm thủ công thú vị cho cả gia đình trong nhà ngói ấm cúng, trà thảo mộc gừng ấm bụng.",
            descriptionEn = "Hands-on indoor family craft under sheltered tiled roof, complimentary warm ginger herbal tea.",
            descriptionKo = "비 오는 날 실내에서 즐기는 따뜻한 가족 도예 및 등불 공예, 몸을 녹이는 생강차 제공.",
            descriptionJa = "雨音を聞きながら楽しむ陶芸とランタン作り、温かい生姜茶サービス付き。",
            estimatedCostVnd = 110_000,
            travelTimeMinutes = 10,
            badges = listOf(
                SmartBadgeType.VERIFIED_LOCAL_PRICE,
                SmartBadgeType.SENIOR_STROLLER_FRIENDLY,
                SmartBadgeType.KID_SAFE_NON_SPICY,
                SmartBadgeType.INDOOR_WEATHER_SAFE
            ),
            locationArea = "Quận Hải Châu",
            isIndoor = true,
            isReroutedAlternate = true
        )
    )

    val sampleSeafoodMenuResult = MenuScanResult(
        restaurantName = "Quán Hải Sản Biển Rạng",
        address = "Đường Nguyễn Tất Thành, Quận Liên Chiểu, Đà Nẵng",
        fairnessScorePercent = 95,
        fairnessVerdictVi = "Chuẩn Giá Bản Địa (Cam kết niêm yết không chặt chém)",
        fairnessVerdictEn = "Verified Local Fair Price (Zero tourist-trap markup detected)",
        fairnessVerdictKo = "현지 정찰제 준수 (바가지 없는 안심 식당 인증)",
        fairnessVerdictJa = "適正価格認定（観光客向け不当上乗せなし）",
        scannedImageRes = R.drawable.sample_menu_seafood,
        dishes = listOf(
            ScannedDishItem(
                id = "dish_1",
                nameVi = "Cua Huỳnh Đế (1 kg tươi sống)",
                nameEn = "Spanner Crab (1 kg fresh from tank)",
                nameKo = "황제 게(스패너 크랩) 1kg 생물",
                nameJa = "皇帝ガニ（アサヒガニ）活け1kg",
                priceVnd = 650_000,
                benchmarkVnd = 680_000,
                priceStatus = PriceStatus.FAIR_PRICE,
                isSpicy = false,
                allergenNoteVi = "Hấp sả gừng / sốt bơ tỏi không cay. Thịt chắc ngọt, an toàn cho bé.",
                allergenNoteEn = "Steamed lemongrass/butter garlic. Sweet, rich meat, kid-safe non-spicy.",
                allergenNoteKo = "레몬글라스 생강 찜, 순한 버터갈릭 소스. 유아도 먹기 좋은 부드러운 살.",
                allergenNoteJa = "レモングラス生姜蒸し、バターガーリック味。甘みがありお子様も安心。"
            ),
            ScannedDishItem(
                id = "dish_2",
                nameVi = "Mực Một Nắng Nướng Sa Tế (đĩa)",
                nameEn = "Sun-Dried Squid Grilled with Satay Chili",
                nameKo = "햇볕에 말린 한치 사테 칠리 구이",
                nameJa = "一夜干しアオリイカのサテ唐辛子炭火焼き",
                priceVnd = 180_000,
                benchmarkVnd = 190_000,
                priceStatus = PriceStatus.FAIR_PRICE,
                isSpicy = true,
                allergenNoteVi = "⚠️ Vị cay nồng từ sa tế ớt hiểm. Không nên cho trẻ nhỏ và người đau dạ dày dùng.",
                allergenNoteEn = "⚠️ Strong chili satay spice. Not recommended for young children or sensitive stomachs.",
                allergenNoteKo = "⚠️ 매운 칠리 사테 양념. 유아 및 매운 음식에 약한 어르신 주의 필요.",
                allergenNoteJa = "⚠️ 辛口サテ唐辛子ソース使用。幼児や胃の弱い方にはおすすめしません。"
            ),
            ScannedDishItem(
                id = "dish_3",
                nameVi = "Tôm Sú Hấp Nước Dừa (đĩa 500g)",
                nameEn = "Tiger Prawns Steamed in Fresh Coconut Water",
                nameKo = "신선한 코코넛 워터 타이거 새우찜",
                nameJa = "天然ブラックタイガーのヤシ果汁スチーム蒸し",
                priceVnd = 220_000,
                benchmarkVnd = 230_000,
                priceStatus = PriceStatus.FAIR_PRICE,
                isSpicy = false,
                allergenNoteVi = "Vị ngọt tự nhiên của dừa xiêm, không ớt, lột vỏ dễ dàng cho người già và bé.",
                allergenNoteEn = "Natural sweetness of fresh coconut, non-spicy, tender for elders and kids.",
                allergenNoteKo = "코코넛 본연의 달콤함, 맵지 않아 온 가족이 즐기기 좋은 대표 인기 메뉴.",
                allergenNoteJa = "ココナッツの優しい甘み、辛さゼロでお年寄りやお子様も食べやすい一品。"
            ),
            ScannedDishItem(
                id = "dish_4",
                nameVi = "Nghêu Biển Hấp Sả (tô lớn)",
                nameEn = "Fresh Clams Steamed with Fragrant Lemongrass",
                nameKo = "향긋한 레몬글라스 조개 맑은 탕",
                nameJa = "大粒ハマグリのレモングラス香り蒸しスープ",
                priceVnd = 85_000,
                benchmarkVnd = 85_000,
                priceStatus = PriceStatus.FAIR_PRICE,
                isSpicy = false,
                allergenNoteVi = "Nước dùng thanh ngọt, nghêu tươi đánh bắt trong ngày, có thể bỏ ớt tỉa nếu muốn hoàn toàn không cay.",
                allergenNoteEn = "Light aromatic clam broth, caught daily. Request no chili for total mildness.",
                allergenNoteKo = "시원한 조개 육수, 당일 직송 신선 조개. 고추를 빼면 아주 순하게 섭취 가능.",
                allergenNoteJa = "澄んだ出汁が美味しい朝獲れハマグリ。唐辛子抜きで完全ノンスパイス対応可。"
            ),
            ScannedDishItem(
                id = "dish_5",
                nameVi = "Ốc Hương Xào Bơ Cay (đĩa)",
                nameEn = "Sweet Snail Sautéed in Spicy Butter",
                nameKo = "매콤 버터 골뱅이(옥흐엉) 볶음",
                nameJa = "スイートスネイル（巻貝）のスパイシーバター炒め",
                priceVnd = 350_000,
                benchmarkVnd = 240_000,
                priceStatus = PriceStatus.TOURIST_TRAP_HIGH,
                isSpicy = true,
                allergenNoteVi = "⚠️ Cảnh báo giá cao hơn 45% so với mức trung bình tại chợ hải sản địa phương!",
                allergenNoteEn = "⚠️ Warning: Priced 45% above Da Nang local seafood market benchmark!",
                allergenNoteKo = "⚠️ 주의: 현지 평균 대비 약 45% 높은 가격으로 책정됨!",
                allergenNoteJa = "⚠️ 注意：ダナン地元相場より約45%割高に設定されています！"
            ),
            ScannedDishItem(
                id = "dish_6",
                nameVi = "Lẩu Cá Cu Đà Nẵng (nồi 4 người)",
                nameEn = "Da Nang Sea Bass Hotpot (Serves 4)",
                nameKo = "다낭 전통 농어 맑은 샤브 전골 (4인분)",
                nameJa = "ダナン名物スズキのハーブ海鮮鍋（4人前）",
                priceVnd = 260_000,
                benchmarkVnd = 270_000,
                priceStatus = PriceStatus.FAIR_PRICE,
                isSpicy = false,
                allergenNoteVi = "Kèm rau muống đồng, bắp chuối, bún tươi. Nước dùng chua thanh dịu nhẹ, dinh dưỡng cho cả nhà.",
                allergenNoteEn = "Served with fresh herbs, banana blossoms, rice noodles. Nourishing mild hotpot.",
                allergenNoteKo = "신선한 야채와 생면 제공. 자극적이지 않고 영양이 풍부한 가족 추천 탕 요리.",
                allergenNoteJa = "新鮮野菜と米麺付き。酸味と旨味が調和した身体に優しい鍋料理。"
            )
        )
    )

    fun calculateRouteSummary(items: List<DestinationItem>): RouteSummary {
        val count = items.size
        val distance = (count * 4.2).coerceAtLeast(8.5)
        val time = (count * 18).coerceAtLeast(45)
        val grabCost = (distance * 14_500).toLong() + 30_000
        return RouteSummary(
            totalDistanceKm = (Math.round(distance * 10) / 10.0),
            totalTravelTimeMinutes = time,
            estimatedGrabTaxiVnd = grabCost,
            stopsCount = count,
            isPriceGuaranteed = true
        )
    }
}
