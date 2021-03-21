package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.service.AllContentsService
import subway.service.DeleteLineService
import subway.service.RegisterLineService
import subway.service.RegisterSectionService
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
            RegisterLineService.register(inputLineNameToRegister()).let {
                RegisterSectionService(
                    Section(
                        line = it,
                        upwardStation = Station(inputUpwardStationName()),
                        downwardStation = Station(inputDownwardStationName()),
                        distance = inputDistance(),
                        time = inputTime()
                    )
                ).register()
            }
            infoMessage()
            registeredLine()
        }
        MENU_TWO -> {
            DeleteLineService.delete(inputLineNameToDelete())
            infoMessage()
            deletedLine()
        }
        MENU_THREE -> allLines(AllContentsService.getLines())
    }
}
