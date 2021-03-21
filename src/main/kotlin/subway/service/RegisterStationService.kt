package subway.service

import subway.domain.Station
import subway.repository.StationRepository

object RegisterStationService {
    private fun validate(name: String) {
        require(!StationRepository.existsByName(name))
    }

    fun register(name: String) {
        validate(name)
        StationRepository.add(Station(name))
    }
}
