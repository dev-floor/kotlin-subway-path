package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.service.AllContentsService
import subway.service.DeleteLineService
import subway.service.RegisterLineService
import subway.service.RegisterSectionService
import subway.view.getDistance
import subway.view.getDownwardStationName
import subway.view.getLineNameToDelete
import subway.view.getLineNameToRegister
import subway.view.getTime
import subway.view.getUpwardStationName
import subway.view.infoMessage
import subway.view.selectNumber
import subway.view.showAdminLine
import subway.view.showAllLines
import subway.view.succeedDeleteLine
import subway.view.succeedRegisterLine

fun adminLine() {
    showAdminLine()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            val line = Line(getLineNameToRegister())
            RegisterLineService(line).register()
            RegisterSectionService(
                Section(
                    line = line,
                    upwardStation = Station(getUpwardStationName()),
                    downwardStation = Station(getDownwardStationName()),
                    distance = getDistance(),
                    time = getTime()
                )
            ).register()
            infoMessage()
            succeedRegisterLine()
        }
        MENU_TWO -> {
            DeleteLineService(getLineNameToDelete()).delete()
            infoMessage()
            succeedDeleteLine()
        }
        MENU_THREE -> showAllLines(AllContentsService().getLines())
    }
}
