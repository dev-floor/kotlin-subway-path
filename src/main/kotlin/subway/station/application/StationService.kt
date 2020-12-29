package subway.station.application

import subway.station.domain.StationRepository
import subway.station.exception.IllegalStationException
import subway.station.presentation.StationRegisterRequest

class StationService(private val stationRepository: StationRepository) {
    fun register(request: StationRegisterRequest) {
        val station = request.toEntity()

        if (stationRepository.exists(station)) {
            throw IllegalStationException("이미 존재하는 역입니다.")
        }

        stationRepository.save(station)
    }

    fun showAll() = stationRepository.findAll()
}
