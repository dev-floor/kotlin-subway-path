package subway.ui.station

import subway.common.application.ServiceHandler.STATION_SERVICE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateInfoMessage

class StationShowView : View {
    override fun render() {
        println("\n## 역 목록")

        STATION_SERVICE.showAll().forEach { println(generateInfoMessage(it.name.name)) }

        ViewNavigation.goToFirst()
    }
}
