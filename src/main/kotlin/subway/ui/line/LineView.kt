package subway.ui.line

import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.LINE

class LineView : View {
    override fun render() {
        println(generateScreenTitle(LINE))
    }
}
