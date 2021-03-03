package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.SectionRepository.existsByUpward
import subway.view.getDownwardNameOfSectionToDelete
import subway.view.getLineNameOfSectionToDelete
import subway.view.getUpwardNameOfSectionToDelete
import subway.view.infoMessage
import subway.view.succeedDeleteSection

const val MIN_STATION_COUNT_IN_SECTION = 1

fun deleteSection() {
    val line = Line(getLineNameOfSectionToDelete())
    val upwardStation = Station(getUpwardNameOfSectionToDelete())
    val downwardStation = Station(getDownwardNameOfSectionToDelete())

    require(
        SectionRepository.findAll()
            .count { it.line.name == line.name } > MIN_STATION_COUNT_IN_SECTION
    )
    require(
        SectionRepository.findAll()
            .any {
                it.upwardStation.name == upwardStation.name &&
                    it.downwardStation.name == downwardStation.name
            }
    )

    if (existsByUpward(line, downwardStation))
        biConnectedSection(upwardStation, downwardStation, line)

    if (downwardStation.isDownwardTerminal) {
        downwardStation.isDownwardTerminal = false
        SectionRepository.findByDownward(line, upwardStation)
            .downwardStation.isDownwardTerminal = true
    }

    SectionRepository.delete(line, upwardStation, downwardStation)

    infoMessage()
    succeedDeleteSection()
}

fun biConnectedSection(upwardStation: Station, downwardStation: Station, line: Line) {
    val downwardSection = SectionRepository.findByUpward(line, downwardStation)
    val upwardSection = SectionRepository.findByDownward(line, downwardStation)
    SectionRepository.add(
        Section(
            line = LineRepository.findByName(line.name),
            upwardStation = upwardStation,
            downwardStation = SectionRepository.findByUpward(line, upwardStation).downwardStation,
            distance = downwardSection.distance!! + upwardSection.distance!!,
            time = downwardSection.time!! + upwardSection.time!!
        )
    )
}
