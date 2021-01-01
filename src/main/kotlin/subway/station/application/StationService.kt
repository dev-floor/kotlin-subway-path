package subway.station.application

import subway.common.exception.ALREADY_EXISTS_STATION
import subway.common.exception.DELETION_NOT_POSSIBLE_STATION_MESSAGE
import subway.common.exception.NOT_EXISTS_STATION
import subway.common.exception.REGISTERED_STATION_ON_SECTION
import subway.section.domain.SectionRepository
import subway.station.domain.Station
import subway.station.domain.StationRepository

class StationService(
    private val stationRepository: StationRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: StationRegisterRequest) {
        require(!stationRepository.existsByName(request.stationName)) { ALREADY_EXISTS_STATION }

        stationRepository.save(request.station)
    }

    fun showAll() = stationRepository.findAll()
        .filter { it != Station.UPWARD_END_STATION }
        .sortedWith(Station)
        .map { StationResponse.from(it) }

    fun remove(request: StationRemoveRequest): Boolean {
        val station = request.station

        require(stationRepository.existsByName(request.stationName)) { NOT_EXISTS_STATION }
        require(!sectionRepository.existsByPreStation(station)) { REGISTERED_STATION_ON_SECTION }
        require(!sectionRepository.existsByStation(station)) { REGISTERED_STATION_ON_SECTION }
        require(station != Station.UPWARD_END_STATION) { DELETION_NOT_POSSIBLE_STATION_MESSAGE }

        return stationRepository.delete(station)
    }
}
