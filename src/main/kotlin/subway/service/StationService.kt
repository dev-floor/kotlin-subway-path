package subway.service

import subway.domain.Station
import subway.repository.SectionRepository
import subway.repository.StationRepository

object StationService {

    fun register(name: String) {
        require(!StationRepository.existsByName(name))
        StationRepository.add(Station(name))
    }

    fun delete(name: String) {
        require(
            SectionRepository.findAll()
                .none { it.matchUpward(name) || it.matchDownward(name) }
        )
        StationRepository.deleteByName(name)
    }

    fun getStations(): List<String> = StationRepository.findAll().map { it.name }
}
