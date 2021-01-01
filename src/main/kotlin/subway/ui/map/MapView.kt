package subway.ui.map

import subway.ui.common.View
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.MAP

class MapView : View {
    override fun render() {
        println(generateScreenTitle(MAP))
    }
}
