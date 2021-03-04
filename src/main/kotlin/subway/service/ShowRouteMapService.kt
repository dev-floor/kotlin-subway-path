package subway.service

import subway.repository.LineRepository
import subway.repository.SectionRepository

class ShowRouteMapService {
    fun routeMap(): List<Any> {
        val routes: MutableList<Any> = mutableListOf()

        LineRepository.findAll().map { it ->
            routes.add(it.name)
            allSectionsInLine(it.name).map {
                routes.add(it)
            }
        }
        return routes.toList()
    }

    private fun allSectionsInLine(name: String): List<Any> {
        val route: MutableList<Any> = mutableListOf()
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
