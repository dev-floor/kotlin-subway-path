package subway.ui.line

import subway.common.application.ServiceHandler
import subway.line.application.LineRegisterRequest
import subway.ui.common.FeatureType.REGISTER
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateSuccessMessage
import subway.ui.main.MainFeature.LINE

class LineRegisterView : View {
    override fun render() {
        println("\n## 등록할 노선 이름을 입력하세요.")
        val lineName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 노선의 상행 종점역 이름을 입력하세요.")
        val preStationName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 노선의 하행 종점역 이름을 입력하세요.")
        val stationName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 노선의 구간 거리을 입력하세요(단위는 km입니다).")
        val distance = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 노선의 소요 시간을 입력하세요(단위는 분입니다).")
        val duration = readLine()?.trim() ?: throw AssertionError()

        val request = LineRegisterRequest.of(
            lineName = lineName,
            preStationName = preStationName,
            stationName = stationName,
            distance = distance,
            duration = duration)
        ServiceHandler.LINE_SERVICE.register(request)
        println(generateSuccessMessage(LINE.category, REGISTER))

        ViewNavigation.goToFirst()
    }
}
