package subway.ui.station

import subway.common.application.ServiceHandler.STATION_SERVICE
import subway.station.application.StationRemoveRequest
import subway.ui.common.FeatureType.REMOVE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateSuccessMessage
import subway.ui.main.MainFeature.STATION

class StationRemoveView : View {
    override fun render() {
        println("\n## 삭제할 역 이름을 입력하세요.")

        val stationName = readLine()?.trim() ?: throw AssertionError()
        val request = StationRemoveRequest(stationName)

        STATION_SERVICE.remove(request)
        println(generateSuccessMessage(STATION.category, REMOVE))

        ViewNavigation.goToFirst()
    }
}
