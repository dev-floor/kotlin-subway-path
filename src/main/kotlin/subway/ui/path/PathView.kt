package subway.ui.path

import subway.ui.common.SELECT_FEATURE
import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.PATH

class PathView : View {
    override fun render() {
        println(generateScreenTitle(PATH))
        PathFeature.values().forEach { println(it.featureInfo) }
        println(SELECT_FEATURE)

        val command = readLine()?.trim() ?: throw IllegalArgumentException()

        PathFeature.from(command)
            .navigate()
    }
}
