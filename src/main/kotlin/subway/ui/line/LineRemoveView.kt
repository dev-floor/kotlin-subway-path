package subway.ui.line

import subway.common.application.ServiceHandler.LINE_SERVICE
import subway.line.application.LineRemoveRequest
import subway.ui.common.FeatureType.REMOVE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateSuccessMessage
import subway.ui.main.MainFeature.LINE

class LineRemoveView : View {
    override fun render() {
        println("\n## 삭제할 노선 이름을 입력하세요.")

        val lineName = readLine()?.trim() ?: throw AssertionError()
        val request = LineRemoveRequest(lineName)

        LINE_SERVICE.remove(request)
        println(generateSuccessMessage(LINE.category, REMOVE))

        ViewNavigation.goToFirst()
    }
}
