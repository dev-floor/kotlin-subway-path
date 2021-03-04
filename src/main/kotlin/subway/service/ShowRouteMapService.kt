package subway.service

import subway.domain.Section
import subway.repository.LineRepository
import subway.repository.SectionRepository

class ShowRouteMapService {
    fun routeMap(): List<String> {
        val routes = mutableListOf<String>()

        LineRepository.findAll().map { it ->
            routes.add(it.name)
            routes.add(SEPARATOR_LINE_WITH_STATION)
            allSectionsInLine(it.name)
            routes.add(SEPARATOR_EACH_LINE)
        }

        return routes.toList()
    }

    private fun allSectionsInLine(name: String): List<String> {
        val route: MutableList<String> = mutableListOf()
        var section = SectionRepository.findAll()
            .first { it.line.name == name && it.upwardStation.isUpwardTerminal }
        while (true) {
            route.add(section.upwardStation.name)
            route.add(distanceAndTime(section))
            if (!SectionRepository.existsByUpward(section.line, section.downwardStation)) break
            section = SectionRepository.findByUpward(section.line, section.downwardStation)
        }
        route.add(section.downwardStation.name)
        return route
    }

    private fun distanceAndTime(section: Section): String =
        section.distance.toString() + KILOMETER + SEPARATOR_DISTANCE_AND_TIME + section.time.toString() + MINUTE

    companion object {
        const val SEPARATOR_LINE_WITH_STATION = "---"

        const val SEPARATOR_EACH_LINE = "\n"

        const val KILOMETER = "km"

        const val MINUTE = "분"

        const val SEPARATOR_DISTANCE_AND_TIME = " / "
    }
}
