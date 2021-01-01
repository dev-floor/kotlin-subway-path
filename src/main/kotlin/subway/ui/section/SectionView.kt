package subway.ui.section

import subway.ui.common.SELECT_FEATURE
import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.SECTION

class SectionView : View {
    override fun render() {
        println(generateScreenTitle(SECTION))
        SectionFeature.values().forEach { println(it.featureInfo) }
        println(SELECT_FEATURE)

        val command = readLine()?.trim() ?: throw IllegalArgumentException()

        SectionFeature.of(command)
            .navigate()
    }
}
