package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.SectionRepository
import subway.view.getDownwardNameOfSectionToRegister
import subway.view.getLineNameOfSectionToRegister
import subway.view.getSectionDistance
import subway.view.getSectionTime
import subway.view.getUpwardNameOfSectionToRegister
import subway.view.infoMessage
import subway.view.succeedRegisterSection

fun registerSection() {
    val line = Line(getLineNameOfSectionToRegister())
    val upwardStationName = getUpwardNameOfSectionToRegister()
    val downwardStationName = getDownwardNameOfSectionToRegister()
    val distance = getSectionDistance()
    val time = getSectionTime()

    val section = Section(line, Station(upwardStationName), Station(downwardStationName), distance, time)

    // Additional section
    if(SectionRepository.existsByUpward(line, section.upwardStation))
        registerAdditionalSection(section)

    SectionRepository.add(section)

    // terminal change
    if (isDownwardTerminal(section.line.name, section.upwardStation.name))
        changeTerminalStation(section)

    infoMessage()
    succeedRegisterSection()
}

fun registerAdditionalSection(section: Section) {
    val upwardSection = SectionRepository.findByUpward(section.upwardStation)
    SectionRepository.delete(section.line, section.upwardStation, upwardSection.downwardStation)
    SectionRepository.add(Section(
        line = section.line,
        upwardStation = section.downwardStation,
        downwardStation = upwardSection.downwardStation))
}

fun changeTerminalStation(section: Section) {
    SectionRepository.findAll().filter {
        it.line.name == section.line.name &&
                it.downwardStation.name == section.upwardStation.name
    }
        .map { it.downwardStation.isDownwardTerminal = false }
    section.downwardStation.isDownwardTerminal = true
}

fun isDownwardTerminal(lineName: String, stationName: String): Boolean =
    SectionRepository.findAll().any {
        it.line.name == lineName
                && it.downwardStation.name == stationName
                && it.downwardStation.isDownwardTerminal
    }