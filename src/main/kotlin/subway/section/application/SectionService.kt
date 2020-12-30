package subway.section.application

import subway.common.exception.NOT_EXISTS_LINE
import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.LineRepository
import subway.section.domain.SectionRepository
import subway.section.presentation.SectionRegisterRequest
import subway.station.domain.StationRepository

class SectionService(
    private val stationRepository: StationRepository,
    private val lineRepository: LineRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: SectionRegisterRequest) {
        require(lineRepository.existsByName(request.lineName)) { NOT_EXISTS_LINE }
        require(stationRepository.existsByName(request.upStationName)) { NOT_EXISTS_STATION }
        require(stationRepository.existsByName(request.downStationName)) { NOT_EXISTS_STATION }

        sectionRepository.save(request.toSection())
    }
}
