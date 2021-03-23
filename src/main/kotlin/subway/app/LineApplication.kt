package subway.app

import subway.domain.Station
import subway.dto.SectionRequest
import subway.service.LineService
import subway.service.SectionService
import subway.view.allLines
import subway.view.deletedLine
import subway.view.infoMessage
import subway.view.inputDistance
import subway.view.inputDownwardStationName
import subway.view.inputLineNameToDelete
import subway.view.inputLineNameToRegister
import subway.view.inputSelect
import subway.view.inputTime
import subway.view.inputUpwardStationName
import subway.view.linePage
import subway.view.registeredLine

fun adminLine() {
    linePage()
    val select = inputSelect()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            LineService.register(inputLineNameToRegister()).let {
                SectionService.register(
                    SectionRequest(
                        line = it,
                        upwardStation = Station(inputUpwardStationName()),
                        downwardStation = Station(inputDownwardStationName()),
                        distance = inputDistance(),
                        time = inputTime()
                    )
                )
            }
            infoMessage()
            registeredLine()
        }
        MENU_TWO -> {
            LineService.delete(inputLineNameToDelete())
            infoMessage()
            deletedLine()
        }

        MENU_THREE -> allLines(LineService.getLines())
    }
}
