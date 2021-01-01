package subway.ui.path

import subway.ui.common.FeatureType
import subway.ui.common.INVALID_COMMAND_MESSAGE
import subway.ui.common.View
import subway.ui.common.ViewNavigation

enum class PathFeature(
    val command: String,
    val featureType: FeatureType,
    private val featureView: View? = null,
) {
    DISTANCE("1", FeatureType.DISTANCE, PathShowView(FeatureType.DISTANCE.name)),
    DURATION("2", FeatureType.DURATION, PathShowView(FeatureType.DURATION.name)),
    BACK("B", FeatureType.BACK);

    val featureInfo get() = "$command. ${featureType.type}"

    fun navigate() = ViewNavigation.navigateWithHandlingException(featureView)

    companion object {
        fun from(command: String) =
            values()
                .find { it.command == command }
                ?: throw IllegalArgumentException(INVALID_COMMAND_MESSAGE)
    }
}
