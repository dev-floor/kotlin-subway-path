package subway.ui.section

import subway.common.application.ServiceHandler.SECTION_SERVICE
import subway.section.application.SectionRegisterRequest
import subway.ui.common.FeatureType.REGISTER
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateSuccessMessage
import subway.ui.main.MainFeature.SECTION

class SectionRegisterView : View {
    override fun render() {
        println("\n## 등록할 구간의 노선 이름을 입력하세요.")
        val lineName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 구간의 이전(상행)역 이름을 입력하세요.")
        val preStationName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 구간의 현재(하행)역 이름을 입력하세요.")
        val stationName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 구간의 구간 거리을 입력하세요(단위는 km).")
        val distance = readLine()?.trim() ?: throw AssertionError()

        println("\n## 등록할 구간의 소요 시간을 입력하세요(단위는 분).")
        val duration = readLine()?.trim() ?: throw AssertionError()

        val request = SectionRegisterRequest.of(
            lineName = lineName,
            preStationName = preStationName,
            stationName = stationName,
            distance = distance,
            duration = duration)
        SECTION_SERVICE.register(request)
        println(generateSuccessMessage(SECTION.category, REGISTER))

        ViewNavigation.goToFirst()
    }
}
