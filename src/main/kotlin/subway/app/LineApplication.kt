package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.init.ONE
import subway.init.THREE
import subway.init.TWO
import subway.repository.LineRepository
import subway.view.*

fun adminLine() {
    showAdminStation()
    select = selectMessage()
    if(select !== BACK) return

    when(select.toInt()){
        ONE -> registerLine()
        TWO -> deleteLine()
        THREE -> showAllLines()
    }
}

fun registerLine() {
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

