package subway.repository

import subway.domain.Section
import subway.domain.Station

const val KILOMETER = "km"

const val MINUTE = "분"

const val SEPARATOR_DISTANCE_AND_TIME = " / "

object SectionRepository {

    private val sections = mutableListOf<Section>()

    fun sections() = sections.toList()

    fun addSection(section: Section) {
        changeTerminalStation(section)
        firstSection(section)

        sections.add(section)
    }

    private fun firstSection(section: Section) {
        if (!existDownwardByName(section.line.name, section.downwardStation.name) &&
            !existUpwardByName(section.line.name, section.upwardStation.name) &&
            !existDownwardByName(section.line.name, section.upwardStation.name) &&
            !existUpwardByName(section.line.name, section.downwardStation.name)
        ) {
            section.upwardStation.isUpwardTerminal = true
            section.downwardStation.isDownwardTerminal = true
        }
    }

    fun changeTerminalStation(section: Section) {

        if (isDownwardTerminal(section.line.name, section.upwardStation.name)) {
            sections().filter {
                it.line.name == section.line.name &&
                    it.downwardStation.name == section.upwardStation.name
            }
                .map { it.downwardStation.isDownwardTerminal = false }
            section.downwardStation.isDownwardTerminal = true
        }
    }

    private fun isDownwardTerminal(lineName: String, stationName: String): Boolean = sections()
        .any {
            it.line.name == lineName &&
                it.downwardStation.name == stationName &&
                it.downwardStation.isDownwardTerminal
        }

    fun existsByDownward(lineName: String, station: Station): Boolean =
        sections().any { it.downwardStation.name == station.name && it.line.name == lineName }

    fun existsByUpward(lineName: String, station: Station): Boolean =
        sections().any { it.upwardStation.name == station.name && it.line.name == lineName }


    fun existDownwardByName(lineName: String, stationName: String): Boolean = sections()
        .any { it.downwardStation.name == stationName && it.line.name == lineName }

    fun existUpwardByName(lineName: String, stationName: String): Boolean = sections()
        .any { it.upwardStation.name == stationName && it.line.name == lineName }

    private fun findUpwardTerminalSection(lineName: String): Section = sections()
        .first { it.line.name == lineName && it.upwardStation.isUpwardTerminal }

    fun findDownwardNameByUpwardName(name: String): String = sections()
        .filter { it.upwardStation.name == name }
        .map { it.downwardStation.name }.first()

    fun existStationInSection(name: String) = sections()
        .any { it.downwardStation.name == name || it.upwardStation.name == name }

    fun deleteSection(lineName: String, upwardName: String, downwardName: String) = sections
        .removeIf {
            it.line.name == lineName &&
                it.upwardStation.name == upwardName &&
                it.downwardStation.name == downwardName
        }

    fun stationCountInSection(name: String) = sections().count { it.line.name == name }

    fun continuousStation(upwardName: String, downwardName: String) = sections()
        .any { it.downwardStation.name == downwardName && it.upwardStation.name == upwardName }

    fun allSectionsInLine(name: String, printMessage: MutableList<String>): List<String> {
        var section = findUpwardTerminalSection(name)
        while (true) {
            printMessage.add(section.upwardStation.name)
            printMessage.add(distanceAndTime(section))
            if (!existUpwardByName(section.line.name, section.downwardStation.name)) break
            section = findByUpwardStation(section.line.name, section.downwardStation)
        }
        printMessage.add(section.downwardStation.name)
        return printMessage
    }

    private fun distanceAndTime(section: Section): String =
        section.distance.toString() + KILOMETER + SEPARATOR_DISTANCE_AND_TIME + section.time.toString() + MINUTE

    private fun findByUpwardStation(lineName: String, station: Station): Section = sections()
        .first { it.line.name == lineName && it.upwardStation.name == station.name }

    fun findSectionByDownwardName(lineName: String, stationName: String): Section = sections()
        .first { it.line.name == lineName && it.downwardStation.name == stationName }

    fun distanceByUpwardName(lineName: String, stationName: String) = sections()
        .first { it.line.name == lineName && it.upwardStation.name == stationName }
        .distance

    fun timeByUpwardName(lineName: String, stationName: String) = sections()
        .first { it.line.name == lineName && it.upwardStation.name == stationName }
        .time
}
