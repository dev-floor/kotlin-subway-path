package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.init.ONE
import subway.init.THREE
import subway.init.TWO
import subway.repository.LineRepository
import subway.repository.StationRepository
import subway.view.*

fun adminLine() {
    showAdminStation()
    select = selectMessage()
    if(select !== BACK){
        when(select.toInt()){
            ONE -> registerLine()
            TWO -> deleteLine()
            THREE -> showAllLines()
        }
    }
}

fun showAllLines() {
    val line = Line(getRegisterLineName())
    val section = Section(getUpwardStationName(), getDownwardStationName(), getDistance(), getTime())

    if(LineRepository.lines().none { it.name === line.name })
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

fun registerLine() {
    TODO("Not yet implemented")
}

fun getLine(): Line = Line(getRegisterLineName())
