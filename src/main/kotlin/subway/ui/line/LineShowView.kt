package subway.ui.line

import subway.common.application.ServiceHandler.LINE_SERVICE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateInfoMessage

class LineShowView : View {
    override fun render() {
        println("\n## 노선 목록")

        LINE_SERVICE.showAll().forEach { println(generateInfoMessage(it.name.name)) }

        ViewNavigation.goToFirst()
    }
}
