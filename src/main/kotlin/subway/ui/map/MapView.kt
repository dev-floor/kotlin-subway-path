package subway.ui.map

import subway.common.application.ServiceHandler.LINE_SERVICE
import subway.common.application.ServiceHandler.SECTION_SERVICE
import subway.section.application.SectionResponse
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
            println(generateInfoMessage(line.name))
            println(SEPARATE_LINE)
            printSections(SECTION_SERVICE.showAllByLine(line.toLine()))
        }

        ViewNavigation.goToFirst()
    }

    private fun printSections(sections: List<SectionResponse>) =
        sections.forEachIndexed { index, (_, _, station, distance, duration) ->
            if (index == 0) return@forEachIndexed println(generateInfoMessage(station))
            println(generateInfoMessage("${distance}km / ${duration}분"))
            println(generateInfoMessage(station))
        }
}
