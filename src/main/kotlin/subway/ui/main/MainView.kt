package subway.ui.main

import subway.ui.common.MAIN_SCREEN_TITLE
import subway.ui.common.SELECT_FEATURE
import subway.ui.common.View

class MainView : View {
    override fun render() {
        println(MAIN_SCREEN_TITLE)
        MainFeature.values().forEach { println(it.featureInfo) }
        println(SELECT_FEATURE)

        val command = readLine()?.trim() ?: throw AssertionError()

        MainFeature.from(command)
            .navigate()
    }
}
