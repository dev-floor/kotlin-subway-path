package subway.app

import subway.domain.Station
import subway.service.AllContentsService
import subway.service.DeleteStationService
import subway.service.RegisterStationService
import subway.view.getStationNameToDelete
import subway.view.getStationNameToRegister
import subway.view.infoMessage
import subway.view.selectNumber
import subway.view.showAdminStation
import subway.view.showAllStations
import subway.view.succeedDeleteStation
import subway.view.succeedRegisterStation

fun adminStation() {
    showAdminStation()
    val select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            val name = getStationNameToRegister()
            RegisterStationService(Station(name)).register()
            infoMessage()
            succeedRegisterStation()
        }
        MENU_TWO -> {
            val name = getStationNameToDelete()
            DeleteStationService(name).delete()
            infoMessage()
            succeedDeleteStation()
        }
        MENU_THREE -> showAllStations(AllContentsService().getStations())
    }
}
