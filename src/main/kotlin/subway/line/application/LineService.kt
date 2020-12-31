package subway.line.application

import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.LineRepository
import subway.section.domain.SectionRepository
import subway.station.domain.StationRepository

class LineService(
    private val stationRepository: StationRepository,
    private val lineRepository: LineRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: LineRegisterRequest) {
        validateStations(request.preStationName, request.stationName)
    }

    private fun validateStations(preStationName: String, stationName: String) {
        require(stationRepository.existsByName(preStationName)) { NOT_EXISTS_STATION }
        require(stationRepository.existsByName(stationName)) { NOT_EXISTS_STATION }
    }
}
