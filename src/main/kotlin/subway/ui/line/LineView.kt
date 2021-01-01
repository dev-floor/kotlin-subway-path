package subway.ui.line

import subway.ui.common.SELECT_FEATURE
import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.LINE

class LineView : View {
    override fun render() {
        println(generateScreenTitle(LINE))
        LineFeature.values().forEach { println(it.featureInfo) }
        println(SELECT_FEATURE)

        val command = readLine()?.trim() ?: throw IllegalArgumentException()

        LineFeature.of(command)
            .navigate()
    }
}
