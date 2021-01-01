package subway.ui.map

import subway.common.application.ServiceHandler.LINE_SERVICE
import subway.common.application.ServiceHandler.SECTION_SERVICE
import subway.section.domain.Section
import subway.ui.common.SEPARATE_LINE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateInfoMessage
import subway.ui.common.generateScreenTitle
import subway.ui.main.MainFeature.MAP

class MapView : View {
    override fun render() {
        println(generateScreenTitle(MAP))

        LINE_SERVICE.showAll().forEachIndexed { index, line ->
            if (index != 0) println()
            println(generateInfoMessage(line.name.name))
            println(SEPARATE_LINE)
            printSections(SECTION_SERVICE.showAllByLine(line))
        }

        ViewNavigation.goToFirst()
    }

    private fun printSections(sections: List<Section>) =
        sections.forEachIndexed { index, section ->
            if (index == 0) return@forEachIndexed println(generateInfoMessage(section.station.name.name))
            println(generateInfoMessage("${section.distance}km / ${section.duration}분"))
            println(generateInfoMessage(section.station.name.name))
        }
}
