package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.init.ONE
import subway.init.THREE
import subway.init.TWO
import subway.repository.LineRepository
import subway.view.*

fun adminLine() {
    showAdminLine()
    select = selectMessage()
    if (select == BACK) return

    when (select.toInt()) {
        ONE -> registerLine()
        TWO -> deleteLine()
        THREE -> showAllLines()
    }
}

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
