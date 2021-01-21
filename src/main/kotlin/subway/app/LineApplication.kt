package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.errorMessage
import subway.view.getDistance
import subway.view.getDownwardStationName
import subway.view.getLineNameToDelete
import subway.view.getLineNameToRegister
import subway.view.getTime
import subway.view.getUpwardStationName
import subway.view.infoMessage
import subway.view.succeedDeleteLine
import subway.view.succeedRegisterLine

fun registerLine() {
    val lineName = getLineNameToRegister()
    val line = Line(lineName)

    val upwardStationName = getUpwardStationName()
    val downwardStationName = getDownwardStationName()
    val distance = getDistance()
    val time = getTime()
    val section = Section(line, Station(upwardStationName), Station(downwardStationName), distance, time)

    require(line.validLineToRegister()) { errorMessage() }
    require(StationRepository.existStationByName(upwardStationName)) // { errorMessage() }
    require(StationRepository.existStationByName(downwardStationName)) // { errorMessage() }

    LineRepository.addLine(line)
    SectionRepository.addSection(section)

    infoMessage()
    succeedRegisterLine()
}

fun deleteLine() {
    val name = getLineNameToDelete()

    LineRepository.deleteLineByName(name)

    infoMessage()
    succeedDeleteLine()
}
