package subway.station.application

import subway.common.exception.ALREADY_EXISTS_STATION
import subway.common.exception.NOT_EXISTS_STATION
import subway.common.exception.REGISTERED_STATION_ON_SECTION
import subway.section.domain.SectionRepository
import subway.station.domain.StationRepository
import subway.station.presentation.StationRegisterRequest
import subway.station.presentation.StationRemoveRequest

class StationService(
    private val stationRepository: StationRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: StationRegisterRequest) {
        require(!stationRepository.existsByName(request.stationName)) { ALREADY_EXISTS_STATION }

        stationRepository.save(request.toStation())
    }

    fun showAll() = stationRepository.findAll()

    fun remove(request: StationRemoveRequest): Boolean {
        val station = request.toStation()

        require(stationRepository.existsByName(request.stationName)) { NOT_EXISTS_STATION }
        require(!sectionRepository.existsByUpStation(station)) { REGISTERED_STATION_ON_SECTION }
        require(!sectionRepository.existsByDownStation(station)) { REGISTERED_STATION_ON_SECTION }

        return stationRepository.delete(station)
    }
}
