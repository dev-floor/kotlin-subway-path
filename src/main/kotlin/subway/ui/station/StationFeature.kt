package subway.ui.station

import subway.ui.common.FeatureType
import subway.ui.common.INVALID_COMMAND_MESSAGE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.main.MainFeature.STATION

enum class StationFeature(
    val command: String,
    private val featureType: FeatureType,
    private val featureView: View? = null,
) {
    REGISTER("1", FeatureType.REGISTER, StationRegisterView()),
    REMOVE("2", FeatureType.REMOVE, StationRemoveView()),
    SHOW("3", FeatureType.SHOW, StationShowView()),
    BACK("B", FeatureType.BACK);

    val featureInfo
        get() = "$command. ${if (this == BACK) "" else "${STATION.category} "}${featureType.type}"

    fun navigate() = ViewNavigation.navigateWithHandlingException(featureView)

    companion object {
        fun from(command: String) =
            values()
                .find { it.command == command }
                ?: throw IllegalArgumentException(INVALID_COMMAND_MESSAGE)
    }
}
