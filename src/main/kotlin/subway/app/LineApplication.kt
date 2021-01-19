package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.view.*


fun registerLine() {
    val line = Line(getRegisterLineName())

    val upwardStation = Station(getUpwardStationName())
    val downwardStation = Station(getDownwardStationName())
    val distance = getDistance()
    val time = getTime()

    val section = Section(upwardStation, downwardStation, distance, time)

    LineRepository.addLine(line)

    infoMessage()
    succeedRegisterLine()
}

fun deleteLine() {
    val name = getDeleteLineName()

    LineRepository.deleteLineByName(name)

    infoMessage()
    succeedDeleteLine()
}
