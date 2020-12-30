package subway.section.application

import subway.common.exception.ALREADY_EXISTS_SECTION
import subway.common.exception.NOT_EXISTS_LINE
import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.LineRepository
import subway.section.domain.Section
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
        require(!sectionRepository.existsByLineAndUpStationAndDownStation(
            request.line,
            request.upStation,
            request.downStation
        )) { ALREADY_EXISTS_SECTION }

        updateAssociatedUpStation(request)
        updateAssociatedDownStation(request)
        sectionRepository.save(request.toSection())
    }

    private fun updateAssociatedUpStation(request: SectionRegisterRequest) =
        sectionRepository.findByLineAndUpStation(request.line, request.upStation)
            ?.let {
                sectionRepository.delete(it)
                sectionRepository.save(it.copy(
                    upStation = request.downStation,
                    distance = Section.INITIAL_DISTANCE,
                    duration = Section.INITIAL_DURATION
                ))
            }

    private fun updateAssociatedDownStation(request: SectionRegisterRequest) =
        sectionRepository.findByLineAndDownStation(request.line, request.downStation)
            ?.let {
                sectionRepository.delete(it)
                sectionRepository.save(it.copy(
                    downStation = request.upStation,
                    distance = Section.INITIAL_DISTANCE,
                    duration = Section.INITIAL_DURATION
                ))
            }
}
