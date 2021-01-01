package subway.ui.section

import subway.common.application.ServiceHandler.SECTION_SERVICE
import subway.section.application.SectionRemoveRequest
import subway.ui.common.FeatureType.REMOVE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateSuccessMessage
import subway.ui.main.MainFeature.SECTION

class SectionRemoveView : View {
    override fun render() {
        println("\n## 삭제할 구간의 노선 이름을 입력하세요.")
        val lineName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 삭제할 구간의 이전(상행)역 이름을 입력하세요.")
        val preStationName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 삭제할 구간의 현재(하행)역 이름을 입력하세요.")
        val stationName = readLine()?.trim() ?: throw AssertionError()

        val request = SectionRemoveRequest(
            lineName = lineName,
            preStationName = preStationName,
            stationName = stationName
        )
        SECTION_SERVICE.remove(request)
        println(generateSuccessMessage(SECTION.category, REMOVE))

        ViewNavigation.goToFirst()
    }
}
