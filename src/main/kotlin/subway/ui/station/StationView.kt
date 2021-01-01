package subway.ui.station

import subway.ui.common.SELECT_FEATURE
import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.STATION

class StationView : View {
    override fun render() {
        println(generateScreenTitle(STATION))
        StationFeature.values().forEach { println(it.featureInfo) }
        println(SELECT_FEATURE)

        val command = readLine()?.trim() ?: throw IllegalArgumentException()

        StationFeature.of(command)
            .navigate()
    }
}
