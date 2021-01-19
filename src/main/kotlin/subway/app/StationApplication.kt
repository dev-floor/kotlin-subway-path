package subway.app

import subway.domain.Station
import subway.repository.StationRepository
import subway.view.*

fun registerStation() {
    val station = Station(getRegisterStationName())

    StationRepository.addStation(station)

    infoMessage()
    succeedRegisterStation()
}

fun deleteStation() {
    val name = getDeleteStationName()

    StationRepository.deleteStationByName(name)

    infoMessage()
    succeedDeleteStation()
}
