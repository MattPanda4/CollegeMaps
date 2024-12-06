package com.example.collegemaps

import org.osmdroid.util.GeoPoint

object BuildingData {




    // FOR EDITING OUTLINES, ALL POINTS START AT THE TOP RIGHT AND GO IN A CLOCKWISE POSITION
    // DOWN TO BOTTOM RIGHT AND BOTTOM LEFT AND ETC ALWAYS STARTING WITH THE TOP RIGHT
    val buildings = mapOf(
        "Dorothy Donohue Hall" to listOf(
            GeoPoint(35.3506804, -119.1030461), // Bottom left
            GeoPoint(35.3502034, -119.1030401),  // Top left
            GeoPoint(35.3501987, -119.1035771), // Top right
            GeoPoint(35.3503566, -119.1035791), // Bottom right
            GeoPoint(35.3503557, -119.1036851),
            GeoPoint(35.3502046, -119.1036831),
            GeoPoint(35.3502009, -119.1041081),
            GeoPoint(35.3506773, -119.1041141),
            GeoPoint(35.3506810, -119.1036851),
            GeoPoint(35.3504498, -119.1036821),
            GeoPoint(35.3504507, -119.1035781),
            GeoPoint(35.3506758, -119.1035811)
        ),
        "Express Cafe" to listOf(
            GeoPoint(35.349917, -119.104333), // Top Right
            GeoPoint(35.349861, -119.104333), // Bottom Right
            GeoPoint(35.349833, -119.104417), // Bottom Left
            GeoPoint(35.349917, -119.104417)  // Top Left

        ),
        "Performance Hall" to listOf(
            GeoPoint(35.351120, -119.104342), // Top Right
            GeoPoint(35.350952, -119.104342), // Bottom Right
            GeoPoint(35.350954, -119.104465), // Bottom Left
            GeoPoint(35.350928, -119.104465),  // Top Left
            GeoPoint(35.350923, -119.104800), // Top Right
            GeoPoint(35.350829, -119.104795), // Bottom Right
            GeoPoint(35.350816, -119.104908), // Top Right
            GeoPoint(35.350899, -119.104913), // Bottom Right
            GeoPoint(35.350899, -119.105009), // Bottom Left
            GeoPoint(35.351039, -119.105017),  // Top Left
            GeoPoint(35.351039, -119.104934), // Top Right
            GeoPoint(35.351094, -119.104940),
            GeoPoint(35.351101, -119.104481),
            GeoPoint(35.351118, -119.104478)
        ),
        "Lecture Building" to listOf(
            GeoPoint(35.351035, -119.105020),
            GeoPoint(35.350860, -119.105017),
            GeoPoint(35.350860, -119.105189),
            GeoPoint(35.351066, -119.105192),
            GeoPoint(35.351068, -119.105074),
            GeoPoint(35.351037, -119.105071)
        ),
        "Administration East" to listOf(
            GeoPoint(35.350910, -119.104910),
            GeoPoint(35.350484, -119.104905),
            GeoPoint(35.350484, -119.105146),
            GeoPoint(35.350858, -119.105146),
            GeoPoint(35.350853, -119.105007),
            GeoPoint(35.350897, -119.105009)
        ),
        "Fine Arts" to listOf(
            GeoPoint(35.351359, -119.104843),
            GeoPoint(35.351276, -119.104838),
            GeoPoint(35.351273, -119.105098),
            GeoPoint(35.351359, -119.105095)
         ),
        "Classroom Building" to listOf(
            GeoPoint(35.351319, -119.105272), // Top Right
            GeoPoint(35.351214, -119.105275), // Bottom Right
            GeoPoint(35.351210, -119.105237), // Bottom Left
            GeoPoint(35.351131, -119.105221),  // Top Left
            GeoPoint(35.351125, -119.105286), // Top Right
            GeoPoint(35.351140, -119.105286), // Bottom Right
            GeoPoint(35.351142, -119.105583), // Top Right
            GeoPoint(35.351125, -119.105597), // Bottom Right
            GeoPoint(35.351125, -119.105650), // Bottom Left
            GeoPoint(35.351199, -119.105659),  // Top Left
            GeoPoint(35.351197, -119.105597), // Top Right
            GeoPoint(35.351241, -119.105597),
            GeoPoint(35.351243, -119.105640),
            GeoPoint(35.351315, -119.105637)
        ),
        "Dore Theatre" to listOf(
            GeoPoint(35.352547, -119.104942), // Top Right
            GeoPoint(35.352415, -119.104940), // Bottom Right
            GeoPoint(35.352413, -119.105157), // Bottom Left
            GeoPoint(35.352079, -119.105154),  // Top Left
            GeoPoint(35.352079, -119.105752), // Top Right
            GeoPoint(35.352179, -119.105755), // Bottom Right
            GeoPoint(35.352181, -119.105696), // Top Right
            GeoPoint(35.352352, -119.105704), // Bottom Right
            GeoPoint(35.352359, -119.105887), // Bottom Left
            GeoPoint(35.352448, -119.105889),  // Top Left
            GeoPoint(35.352459, -119.105581), // Top Right
            GeoPoint(35.352369, -119.105570),
            GeoPoint(35.352361, -119.105304),
            GeoPoint(35.352492, -119.105310),
            GeoPoint(35.352496, -119.105109),
            GeoPoint(35.352534, -119.105101)
        ),
        "Music Building" to listOf(
            GeoPoint(35.351982, -119.105532), // Top Right
            GeoPoint(35.351807, -119.105519), // Bottom Right
            GeoPoint(35.351814, -119.105613), // Bottom Left
            GeoPoint(35.351777, -119.105616),  // Top Left
            GeoPoint(35.351783, -119.105790), // Top Right
            GeoPoint(35.351807, -119.105793), // Bottom Right
            GeoPoint(35.351818, -119.105878), // Top Right
            GeoPoint(35.351532, -119.105881), // Bottom Right
            GeoPoint(35.351529, -119.106061), // Bottom Left
            GeoPoint(35.351853, -119.106061),  // Top Left
            GeoPoint(35.351858, -119.106007), // Top Right
            GeoPoint(35.352004, -119.106013),
            GeoPoint(35.352002, -119.105887),
            GeoPoint(35.352024, -119.105884),
            GeoPoint(35.352011, -119.105790),
            GeoPoint(35.351989, -119.105785)
        ),

        "Humanities Complex" to listOf(
            GeoPoint(35.3520180, -119.1065838), // Top Right
            GeoPoint(35.3519512, -119.1065838), // Bottom Right
            GeoPoint(35.3519502, -119.1062404), // Bottom Left
            GeoPoint(35.3519121, -119.1062453),  // Top Left
            GeoPoint(35.3518638, -119.1062449), // Top Right
            GeoPoint(35.3518658, -119.1065837), // Bottom Right
            GeoPoint(35.3516392, -119.1065865), // Top Right
            GeoPoint(35.3516385, -119.1066810), // Bottom Right
            GeoPoint(35.3518668, -119.1066839), // Bottom Left
            GeoPoint(35.3518664, -119.1067236),  // Top Left
            GeoPoint(35.3518704, -119.1067237), // Top Right
            GeoPoint(35.3518708, -119.1067645),
            GeoPoint(35.3517903, -119.1067653),
            GeoPoint(35.3517910, -119.1068136),
            GeoPoint(35.3517743, -119.1068140),
            GeoPoint(35.3517730, -119.1069592),
            GeoPoint(35.3518407, -119.1069603), // Top Right
            GeoPoint(35.3518417, -119.1070014), // Bottom Right
            GeoPoint(35.3518814, -119.1070022), // Bottom Left
            GeoPoint(35.3518819, -119.1069894),  // Top Left
            GeoPoint(35.3519400, -119.1069900), // Top Right
            GeoPoint(35.3519400, -119.1069943), // Bottom Right
            GeoPoint(35.3519499, -119.1069943), // Top Right
            GeoPoint(35.3519510, -119.1069593), // Bottom Right
            GeoPoint(35.3520456, -119.1069609), // Bottom Left
            GeoPoint(35.3520463, -119.1068111),  // Top Left
            GeoPoint(35.3520263, -119.1068111), // Top Right
            GeoPoint(35.3520263, -119.1067657),
            GeoPoint(35.3519465, -119.1067629),
            GeoPoint(35.3519495, -119.1066839),
            GeoPoint(35.3520168, -119.1066850)
        ),
        "Visual Arts" to listOf(
            GeoPoint(35.3517528, -119.1070481), // Top Right
            GeoPoint(35.3516069, -119.1070489), // Bottom Right
            GeoPoint(35.3516066, -119.1070096), // Bottom Left
            GeoPoint(35.3514902, -119.1070120),  // Top Left
            GeoPoint(35.3514906, -119.1070466), // Top Right
            GeoPoint(35.3514174, -119.1070485), // Bottom Right
            GeoPoint(35.3514183, -119.1076492), // Top Right
            GeoPoint(35.3514889, -119.1076497), // Bottom Right
            GeoPoint(35.3514891, -119.1076577), // Bottom Left
            GeoPoint(35.3514980, -119.1076571),  // Top Left
            GeoPoint(35.3514977, -119.1077214), // Top Right
            GeoPoint(35.3516025, -119.1077188),
            GeoPoint(35.3516031, -119.1076621),
            GeoPoint(35.3516396, -119.1076607),
            GeoPoint(35.3516393, -119.1076263),
            GeoPoint(35.3517153, -119.1076263),
            GeoPoint(35.3517243, -119.1074725), // Top Right
            GeoPoint(35.3516027, -119.1074724), // Bottom Right
            GeoPoint(35.3516043, -119.1072007), // Bottom Left
            GeoPoint(35.3517428, -119.1072007)  // Top Left
        ),
        "Education Building" to listOf(
            GeoPoint(35.3504502, -119.1043586),
            GeoPoint(35.3504069, -119.1043588),
            GeoPoint(35.3504068, -119.1042991),
            GeoPoint(35.3499975, -119.1043011),
            GeoPoint(35.3499969, -119.1046504),
            GeoPoint(35.3504511, -119.1046505)

        ),
        "Student Services" to listOf(
            GeoPoint(35.3504193, -119.1046507),  // Top Left
            GeoPoint(35.3499969, -119.1046504), // Top Right
            GeoPoint(35.3499957, -119.1052972), // Bottom Right
            GeoPoint(35.3503289, -119.1052906), // Top Right
            GeoPoint(35.3503292, -119.1052256), // Bottom Right
            GeoPoint(35.3504290, -119.1052313),
            GeoPoint(35.3504295, -119.1051484),// Bottom Left
            GeoPoint(35.3504882, -119.1051477),  // Top Left
            GeoPoint(35.3504883, -119.1050478), // Top Right
            GeoPoint(35.3504165, -119.1050475)
        ),
        "Romberg Nursing Center" to listOf(
            GeoPoint(35.3499133, -119.1045051),
            GeoPoint(35.3498748, -119.1045058),
            GeoPoint(35.3498740, -119.1044447),
            GeoPoint(35.3495564, -119.1044508),
            GeoPoint(35.3495594, -119.1046876),
            GeoPoint(35.3495458, -119.1046878),
            GeoPoint(35.3495484, -119.1048887),
            GeoPoint(35.3498551, -119.1048828),
            GeoPoint(35.3498521, -119.1046524),
            GeoPoint(35.3499152, -119.1046512)
        ),

        "Central Plant Operations" to listOf(
            GeoPoint(35.3499963, -119.1048851),
            GeoPoint(35.3495041, -119.1048911),
            GeoPoint(35.3495042, -119.1049773),
            GeoPoint(35.3495567, -119.1049772),
            GeoPoint(35.3495568, -119.1051704),
            GeoPoint(35.3494981, -119.1051705),
            GeoPoint(35.3494982, -119.1053008),
            GeoPoint(35.3499869, -119.1052962)
        ),
        "Administration" to listOf(
            GeoPoint(35.3504955, -119.1052256),
            GeoPoint(35.3503292, -119.1052256),
            GeoPoint(35.3503295, -119.1055856),
            GeoPoint(35.3502364, -119.1055866),
            GeoPoint(35.3502366, -119.1056446),
            GeoPoint(35.3503089, -119.1056446),
            GeoPoint(35.3503092, -119.1057826),
            GeoPoint(35.3504582, -119.1057816),
            GeoPoint(35.3504579, -119.1056416),
            GeoPoint(35.3503981, -119.1056416),
            GeoPoint(35.3503977, -119.1054266),
            GeoPoint(35.3504286, -119.1054266),
            GeoPoint(35.3504286, -119.1054406),
            GeoPoint(35.3504960, -119.1054406)
        ),

        "Administration West" to listOf(
            GeoPoint(35.3504900, -119.1059090),
            GeoPoint(35.3504192, -119.1059084),
            GeoPoint(35.3504197, -119.1058218),
            GeoPoint(35.3503902, -119.1058216),
            GeoPoint(35.3503982, -119.1057826),
            GeoPoint(35.3503553, -119.1057816),
            GeoPoint(35.3503626, -119.1061752),
            GeoPoint(35.3504884, -119.1061806)
    ),
        "University Advancement" to listOf(
            GeoPoint(35.3504543, -119.1061762),
            GeoPoint(35.3503626, -119.1061752),
            GeoPoint(35.3503625, -119.1062072),
            GeoPoint(35.3502811, -119.1062072),
            GeoPoint(35.3502805, -119.1063102),
            GeoPoint(35.3503135, -119.1063102),
            GeoPoint(35.3503122, -119.1065462),
            GeoPoint(35.3503662, -119.1065472),
            GeoPoint(35.3503664, -119.1064942),
            GeoPoint(35.3504127, -119.1064942),
            GeoPoint(35.3504129, -119.1064412),
            GeoPoint(35.3504555, -119.1064412),
            GeoPoint(35.3504557, -119.1063882),
            GeoPoint(35.3504997, -119.1063882),
            GeoPoint(35.3504999, -119.1063302),
            GeoPoint(35.3505432, -119.1063312),
            GeoPoint(35.3505435, -119.1062802),
            GeoPoint(35.3504916, -119.1062802),
            GeoPoint(35.3504919, -119.1062112),
            GeoPoint(35.3504541, -119.1062102)
        ),
        "Science Building I" to listOf(
            GeoPoint(35.3498192, -119.1035830),
            GeoPoint(35.3496184, -119.1035815),
            GeoPoint(35.3496181, -119.1036280),
            GeoPoint(35.3494540, -119.1036268),
            GeoPoint(35.3494528, -119.1038730),
            GeoPoint(35.3496201, -119.1038743),
            GeoPoint(35.3496190, -119.1040908),
            GeoPoint(35.3498166, -119.1040923),
            GeoPoint(35.3498166, -119.1040923)

        ),
        "Science Building II" to listOf(
            GeoPoint(35.3498022, -119.1030095),
            GeoPoint(35.3494695, -119.1030095),
            GeoPoint(35.3494695, -119.1034383),
            GeoPoint(35.3498022, -119.1034384)

        ),
        "Science Building III" to listOf(
            GeoPoint(35.3491946, -119.1034707),
            GeoPoint(35.3489968, -119.1034679),
            GeoPoint(35.3489973, -119.1034090),
            GeoPoint(35.3489262, -119.1034080),
            GeoPoint(35.3489257, -119.1034638),
            GeoPoint(35.3488739, -119.1034631),
            GeoPoint(35.3488711, -119.1037770),
            GeoPoint(35.3489299, -119.1037778),
            GeoPoint(35.3489299, -119.1037778),
            GeoPoint(35.3489265, -119.1041550),
            GeoPoint(35.3489967, -119.1041559),
            GeoPoint(35.3489981, -119.1040063),
            GeoPoint(35.3491898, -119.1040089)
        ),

        "Runner Cafe" to listOf(
            GeoPoint(35.3509800, -119.1019702),
            GeoPoint(35.3508228, -119.1019682),
            GeoPoint(35.3508222, -119.1020382),
            GeoPoint(35.3505264, -119.1020352),
            GeoPoint(35.3505229, -119.1024272),
            GeoPoint(35.3507609, -119.1024302),
            GeoPoint(35.3507583, -119.1027222),
            GeoPoint(35.3510072, -119.1027252),
            GeoPoint(35.3510109, -119.1023112),
            GeoPoint(35.3509769, -119.1023112)
        ),

        "Modular East" to listOf(
            GeoPoint(35.3506923, -119.1012118),
            GeoPoint(35.3505436, -119.1012098),
            GeoPoint(35.3505418, -119.1014135),
            GeoPoint(35.3506905, -119.1014155)

        ),
        "Greenhouse" to listOf(


            GeoPoint(35.3504868, -119.1012159),
            GeoPoint(35.350429, -119.101206),
            GeoPoint(35.3504422, -119.1011285),
            GeoPoint(35.3504434, -119.1010493),
            GeoPoint(35.3502993, -119.1010460),
            GeoPoint(35.3502981, -119.1011252),
            GeoPoint(35.350352, -119.101136),
            GeoPoint(35.3503428, -119.1012127),
            GeoPoint(35.3503416, -119.1012919),
            GeoPoint(35.3504856, -119.1012951)
        ),
        "SRC" to listOf(
            GeoPoint(35.3492039, -119.1013857),
            GeoPoint(35.3492041, -119.1013657),
            GeoPoint(35.3491985, -119.1013657),
            GeoPoint(35.3491847, -119.1013647),
            GeoPoint(35.3491800, -119.1013207),
            GeoPoint(35.3491692, -119.1012777),
            GeoPoint(35.3491526, -119.1012367),
            GeoPoint(35.3491306, -119.1012007),
            GeoPoint(35.3491040, -119.1011697),
            GeoPoint(35.3490733, -119.1011447),
            GeoPoint(35.3490396, -119.1011257),
            GeoPoint(35.3490039, -119.1011147),
            GeoPoint(35.3489671, -119.1011117),
            GeoPoint(35.3489672, -119.1010837),
            GeoPoint(35.3486781, -119.1010827),
            GeoPoint(35.3486766, -119.1017267),
            GeoPoint(35.3487166, -119.1017267),
            GeoPoint(35.3487151, -119.1021107),
            GeoPoint(35.3491234, -119.1021097),
            GeoPoint(35.3491235, -119.1020757),
            GeoPoint(35.3491236, -119.1020097),
            GeoPoint(35.3491360, -119.1020097),
            GeoPoint(35.3491986, -119.1020097),
            GeoPoint(35.3491994, -119.1019147),
            GeoPoint(35.3491754, -119.1019147),
            GeoPoint(35.3491770, -119.1017277),
            GeoPoint(35.3492010, -119.1017277)
        ),

        "BookStore" to listOf(
            GeoPoint(35.3502314, -119.1016893),
            GeoPoint(35.3497443, -119.1010723),
            GeoPoint(35.3495714, -119.1012793),
            GeoPoint(35.3496118, -119.1013343),
            GeoPoint(35.3495911, -119.1013653),
            GeoPoint(35.3495741, -119.1013923),
            GeoPoint(35.3495663, -119.1014283),
            GeoPoint(35.3495659, -119.1014803),
            GeoPoint(35.3495673, -119.1015303),
            GeoPoint(35.3495817, -119.1015783),
            GeoPoint(35.3495963, -119.1016103),
            GeoPoint(35.3496368, -119.1016613),
            GeoPoint(35.3497195, -119.1015533),
            GeoPoint(35.3499972, -119.1018903),
            GeoPoint(35.3499390, -119.1019573),
            GeoPoint(35.3499796, -119.1019903),
            GeoPoint(35.3500074, -119.1019993),
            GeoPoint(35.3500427, -119.1020023),
            GeoPoint(35.3500631, -119.1020003),
            GeoPoint(35.3501005, -119.1019823),
            GeoPoint(35.3501192, -119.1019673),
            GeoPoint(35.3501418, -119.1019373),
            GeoPoint(35.3501675, -119.1019743),
            GeoPoint(35.3502181, -119.1019203),
            GeoPoint(35.3501758, -119.1018673),
            GeoPoint(35.3502116, -119.1018183),
            GeoPoint(35.3501693, -119.1017703)




















        )


    )
}