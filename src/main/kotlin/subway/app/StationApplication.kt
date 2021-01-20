package subway.app

import subway.domain.Station
import subway.repository.StationRepository
import subway.view.*

fun registerStation() {
    val name = getStationNameToRegister()
    val station = Station(name)

    require(station.validStationToRegister())
    StationRepository.addStation(station)

    infoMessage()
    succeedRegisterStation()
}

fun deleteStation() {
    val name = getStationNameToDelete()

    require(StationRepository.validStationToDelete(name))
    StationRepository.deleteStationByName(name)

    infoMessage()
    succeedDeleteStation()
}
