package subway.service

import subway.repository.LineRepository
import subway.repository.SectionRepository

class ShowRouteMapService {
    fun routeMap(): List<List<Any>> {
        val routes: MutableList<List<Any>> = mutableListOf()

        LineRepository.findAll().map { it ->
            routes.add(allSectionsInLine(it.name))
        }
        return routes.toList()
    }

    private fun allSectionsInLine(name: String): List<Any> {
        val route: MutableList<Any> = mutableListOf(name)
        var section = SectionRepository.findAll()
            .first { it.line.name == name && it.upwardStation.isUpwardTerminal }
        while (true) {
            route.add(section.upwardStation.name)
            route.add(section.distance!!)
            route.add(section.time!!)
            if (!SectionRepository.existsByUpward(section.line, section.downwardStation)) break
            section = SectionRepository.findByUpward(section.line, section.downwardStation)
        }
        route.add(section.downwardStation.name)
        return route
    }
}
