package subway.domain

import subway.repository.LineRepository
import subway.repository.StationRepository

class Station(val name: String) {

    fun addStation() = StationRepository.addStation(Station(this.name))

    fun deleteStation() {

        StationRepository.deleteStationByName(this.name)
    }

}
