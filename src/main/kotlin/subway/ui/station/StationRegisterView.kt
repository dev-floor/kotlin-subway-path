package subway.ui.station

import subway.common.application.ServiceHandler.STATION_SERVICE
import subway.station.application.StationRegisterRequest
import subway.ui.common.FeatureType.REGISTER
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateSuccessMessage
import subway.ui.main.MainFeature.STATION

class StationRegisterView : View {
    override fun render() {
        println("\n## 등록할 역 이름을 입력하세요.")

        val stationName = readLine()?.trim() ?: throw AssertionError()
        val request = StationRegisterRequest(stationName)

        STATION_SERVICE.register(request)
        println(generateSuccessMessage(STATION.category, REGISTER))

        ViewNavigation.goToFirst()
    }
}
