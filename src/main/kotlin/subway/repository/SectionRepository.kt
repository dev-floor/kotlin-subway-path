package subway.repository

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station

const val KILOMETER = "km"

const val MINUTE = "분"

const val SEPARATOR_DISTANCE_AND_TIME = " / "

object SectionRepository {
    private val sections = mutableListOf<Section>()

    private fun sections() = sections.toList()

    fun add(section: Section) = sections.add(section)

    fun findAll() = sections()

    fun findByUpward(line: Line, station: Station) =
        sections().first { it.upwardStation.name == station.name && it.line.name == line.name }

    fun findByDownward(line: Line, station: Station) =
        sections().first { it.downwardStation.name == station.name && it.line.name == line.name }

    fun existsByDownward(line: Line, station: Station): Boolean =
        sections().any { it.downwardStation.name == station.name && it.line.name == line.name }

    fun existsByUpward(line: Line, station: Station): Boolean =
        sections().any { it.upwardStation.name == station.name && it.line.name == line.name }

    private fun findUpwardTerminalSection(lineName: String): Section = sections()
        .first { it.line.name == lineName && it.upwardStation.isUpwardTerminal }

    fun delete(line: Line, upward: Station, downward: Station) =
        sections.removeIf {
            it.line.name == line.name &&
                it.upwardStation.name == upward.name &&
                it.downwardStation.name == downward.name
        }

    fun allSectionsInLine(name: String, printMessage: MutableList<String>): List<String> {
        var section = findUpwardTerminalSection(name)
        while (true) {
            printMessage.add(section.upwardStation.name)
            printMessage.add(distanceAndTime(section))
            if (!existsByUpward(section.line, section.downwardStation)) break
            section = findByUpwardStation(section.line.name, section.downwardStation)
        }
        printMessage.add(section.downwardStation.name)
        return printMessage
    }

    private fun distanceAndTime(section: Section): String =
        section.distance.toString() + KILOMETER + SEPARATOR_DISTANCE_AND_TIME + section.time.toString() + MINUTE

    private fun findByUpwardStation(lineName: String, station: Station): Section = sections()
        .first { it.line.name == lineName && it.upwardStation.name == station.name }
}
