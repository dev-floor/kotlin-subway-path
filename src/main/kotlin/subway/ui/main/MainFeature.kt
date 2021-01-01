package subway.ui.main

import subway.ui.common.FeatureType
import subway.ui.common.INVALID_COMMAND_MESSAGE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.line.LineView
import subway.ui.map.MapView
import subway.ui.path.PathView
import subway.ui.section.SectionView
import subway.ui.station.StationView

enum class MainFeature(
    val command: String,
    val featureType: FeatureType,
    val category: String = "",
    val featureView: View? = null,
) {
    STATION("1", FeatureType.MANAGE, "역", StationView()),
    LINE("2", FeatureType.MANAGE, "노선", LineView()),
    SECTION("3", FeatureType.MANAGE, "구간", SectionView()),
    MAP("4", FeatureType.PRINT, "지하철 노선도", MapView()),
    PATH("5", FeatureType.SHOW, "경로", PathView()),
    QUIT("Q", FeatureType.QUIT);

    val featureInfo
        get() = "$command. ${if (this == QUIT) "" else "$category "}${featureType.type}"

    fun navigate() = ViewNavigation.navigate(featureView)

    companion object {
        fun from(command: String) =
            values()
                .find { it.command == command }
                ?: throw IllegalArgumentException(INVALID_COMMAND_MESSAGE)
    }
}
