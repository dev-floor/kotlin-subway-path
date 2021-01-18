package subway.app

import subway.domain.Station
import subway.init.ONE
import subway.init.THREE
import subway.init.TWO
import subway.repository.StationRepository
import subway.view.*

fun adminStation() {
    showAdminStation()
    select = selectMessage()
    if(select !== BACK){

        when(select.toInt()){
            ONE -> registerStation()
            TWO -> deleteStation()
            THREE -> showAllStations()
        }
    }
}

fun showAllStations() {
    getStationNames()
    StationRepository.stations.map {
        infoMessage()
        getStationName(it.name)
    }
    newLine()
}

fun registerStation() {
    val station = Station(getRegisterStationName())

    if(StationRepository.stations().none { it.name === station.name })
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