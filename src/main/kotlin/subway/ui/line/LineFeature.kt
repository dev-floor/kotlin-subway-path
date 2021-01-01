package subway.ui.line

import subway.ui.common.FeatureType
import subway.ui.common.INVALID_COMMAND_MESSAGE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.main.MainFeature.LINE

enum class LineFeature(
    val command: String,
    val featureType: FeatureType,
    private val featureView: View? = null,
) {
    REGISTER("1", FeatureType.REGISTER, LineRegisterView()),
    REMOVE("2", FeatureType.REMOVE, LineRemoveView()),
    SHOW("3", FeatureType.SHOW, LineShowView()),
    BACK("B", FeatureType.BACK);

    val featureInfo
        get() = "$command. ${if (this == BACK) "" else "${LINE.category} "}${featureType.type}"

    fun navigate() = ViewNavigation.navigateWithHandlingException(featureView)

    companion object {
        fun of(command: String) =
            values()
                .find { it.command == command }
                ?: throw IllegalArgumentException(INVALID_COMMAND_MESSAGE)
    }
}
