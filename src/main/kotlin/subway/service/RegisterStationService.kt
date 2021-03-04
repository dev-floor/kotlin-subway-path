package subway.service

import subway.domain.Station
import subway.repository.StationRepository

class RegisterStationService(val station: Station) {
    init {
        require(!StationRepository.existsByName(station.name))
    }

    fun register() = StationRepository.add(station)
}
