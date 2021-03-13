package subway.app

import subway.domain.Station
import subway.service.AllContentsService
import subway.service.DeleteStationService
import subway.service.RegisterStationService
import subway.view.allStations
import subway.view.deletedStation
import subway.view.infoMessage
import subway.view.inputSelect
import subway.view.inputStationNameToDelete
import subway.view.inputStationNameToRegister
import subway.view.registeredStation
import subway.view.stationPage

fun adminStation() {
    stationPage()
    val select = inputSelect()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            val name = inputStationNameToRegister()
            RegisterStationService(Station(name)).register()
            infoMessage()
            registeredStation()
        }
        MENU_TWO -> {
            val name = inputStationNameToDelete()
            DeleteStationService(name).delete()
            infoMessage()
            deletedStation()
        }
        MENU_THREE -> allStations(AllContentsService().getStations())
    }
}
