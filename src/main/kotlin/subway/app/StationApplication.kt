package subway.app

import subway.domain.Station
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.getStationNameToDelete
import subway.view.getStationNameToRegister
import subway.view.infoMessage
import subway.view.succeedDeleteStation
import subway.view.succeedRegisterStation

fun registerStation() {
    val name = getStationNameToRegister()
    val station = Station(name)

    require(station.validStationToRegister())
    StationRepository.add(station)

    infoMessage()
    succeedRegisterStation()
}

fun deleteStation() {
    val name = getStationNameToDelete()

    require(
        !SectionRepository.findAll()
            .any { it.downwardStation.name == name || it.upwardStation.name == name }
    )
    StationRepository.deleteByName(name)

    infoMessage()
    succeedDeleteStation()
}
