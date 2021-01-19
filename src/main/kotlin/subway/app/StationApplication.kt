package subway.app

import subway.domain.Station
import subway.view.*

fun registerStation() {
    val name = getStationNameToRegister()
    val station = Station(name)

    station.addStation()

    infoMessage()
    succeedRegisterStation()
}

fun deleteStation() {
    val name = getStationNameToDelete()
    val station = Station(name)

    station.deleteStation()

    infoMessage()
    succeedDeleteStation()
}

