package subway.ui.path

import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.PATH

class PathView : View {
    override fun render() {
        println(generateScreenTitle(PATH))
    }
}
