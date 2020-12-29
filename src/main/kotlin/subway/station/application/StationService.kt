package subway.station.application

import subway.section.domain.SectionRepository
import subway.station.domain.StationRepository
import subway.station.exception.IllegalStationException
import subway.station.presentation.StationRegisterRequest
import subway.station.presentation.StationRemoveRequest

class StationService(
    private val stationRepository: StationRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: StationRegisterRequest) {
        val station = request.toEntity()

        if (stationRepository.exists(station)) {
            throw IllegalStationException("이미 존재하는 역입니다.")
        }

        stationRepository.save(station)
    }

    fun showAll() = stationRepository.findAll()

    fun remove(request: StationRemoveRequest): Boolean {
        val station = request.toEntity()

        if (stationRepository.exists(station).not()) {
            throw IllegalStationException("존재하지 않는 역입니다.")
        }

        if (sectionRepository.existsByStation(station)) {
            throw IllegalStationException("구간에 등록되어 있는 역입니다.")
        }

        return stationRepository.delete(station)
    }
}
