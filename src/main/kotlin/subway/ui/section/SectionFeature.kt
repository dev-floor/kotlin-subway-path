package subway.ui.section

import subway.ui.common.FeatureType
import subway.ui.common.INVALID_COMMAND_MESSAGE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.main.MainFeature.SECTION

enum class SectionFeature(
    val command: String,
    val featureType: FeatureType,
    private val featureView: View? = null,
) {
    REGISTER("1", FeatureType.REGISTER, SectionRegisterView()),
    REMOVE("2", FeatureType.REMOVE, SectionRemoveView()),
    BACK("B", FeatureType.BACK);

    val featureInfo
        get() = "$command. ${if (this == BACK) "" else "${SECTION.category} "}${featureType.type}"

    fun navigate() = ViewNavigation.navigateWithHandlingException(featureView)

    companion object {
        fun of(command: String) =
            values()
                .find { it.command == command }
                ?: throw IllegalArgumentException(INVALID_COMMAND_MESSAGE)
    }
}
