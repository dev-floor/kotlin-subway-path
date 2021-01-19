package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.view.*

fun registerLine() {
    val line = Line(getLineNameToRegister())

    val upwardStation = Station(getUpwardStationName())
    val downwardStation = Station(getDownwardStationName())
    val distance = getDistance()
    val time = getTime()

    val section = Section(upwardStation, downwardStation, distance, time)

    line.addLine()

    infoMessage()
    succeedRegisterLine()
}

fun deleteLine() {
    val name = getLineNameToDelete()

    LineRepository.deleteLineByName(name)

    infoMessage()
    succeedDeleteLine()
}
