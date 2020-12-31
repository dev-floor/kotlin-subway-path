package subway.line.application

import subway.common.exception.ALREADY_EXISTS_LINE
import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.Line
import subway.line.domain.LineRepository
import subway.section.domain.SectionRepository
import subway.station.domain.StationRepository

class LineService(
    private val stationRepository: StationRepository,
    private val lineRepository: LineRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: LineRegisterRequest) {
        require(stationRepository.existsByName(request.preStationName)) { NOT_EXISTS_STATION }
        require(stationRepository.existsByName(request.stationName)) { NOT_EXISTS_STATION }
        require(!lineRepository.existsByName(request.lineName)) { ALREADY_EXISTS_LINE }

        lineRepository.save(request.line)
        sectionRepository.saveAll(request.upwardSection, request.section)
    }

    fun showAll() = lineRepository.findAll()
        .sortedWith(Line)
}
