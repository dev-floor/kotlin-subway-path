package subway.section.application

import subway.common.exception.ALREADY_EXISTS_SECTION
import subway.common.exception.INVALID_SECTION_MESSAGE
import subway.common.exception.INVALID_SECTION_SIZE_MESSAGE
import subway.common.exception.NOT_EXISTS_LINE
import subway.common.exception.NOT_EXISTS_SECTION
import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.Line
import subway.line.domain.LineRepository
import subway.section.domain.Section
import subway.section.domain.SectionRepository
import subway.station.domain.Station
import subway.station.domain.StationRepository

class SectionService(
    private val stationRepository: StationRepository,
    private val lineRepository: LineRepository,
    private val sectionRepository: SectionRepository,
) {
    fun register(request: SectionRegisterRequest) {
        validateLineAndStations(request.lineName, request.preStationName, request.stationName)
        validateSectionToRegister(request.line, request.preStation, request.station)
        validateCycle(request.line, request.preStation, request.station)

        modifySectionAssociatedWithRegistration(request.line, request.preStation, request.station)
        sectionRepository.save(request.toSection())
    }

    private fun validateLineAndStations(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
        require(lineRepository.existsByName(lineName)) { NOT_EXISTS_LINE }
        require(stationRepository.existsByName(preStationName)) { NOT_EXISTS_STATION }
        require(stationRepository.existsByName(stationName)) { NOT_EXISTS_STATION }
    }

    private fun validateSectionToRegister(
        line: Line,
        preStation: Station,
        station: Station,
    ) {
        require(!sectionRepository.existsByLineAndPreStationAndStation(line, preStation, station)) {
            ALREADY_EXISTS_SECTION
        }
        require(!sectionRepository.existsByLineAndStation(line, station)) {
            INVALID_SECTION_MESSAGE
        }
    }

    private fun validateCycle(
        line: Line,
        preStation: Station,
        station: Station,
    ) = require(!sectionRepository.existsByLineAndPreStation(line, station)
            || !sectionRepository.existsByLineAndStation(line, preStation)) {
        INVALID_SECTION_MESSAGE
    }

    private fun modifySectionAssociatedWithRegistration(
        line: Line,
        preStation: Station,
        station: Station,
    ) = sectionRepository.findByLineAndPreStation(line, preStation)
        ?.let {
            sectionRepository.delete(it)
            sectionRepository.save(Section(it.line, station, it.station))
        }

    fun remove(request: SectionRemoveRequest): Boolean {
        val (lineName, preStationName, stationName) = request

        validateLineAndStations(lineName, preStationName, stationName)
        validateExistingSection(lineName, preStationName, stationName)

        modifySectionAssociatedPreStationWhenRemoval(lineName, preStationName, stationName)
        modifySectionAssociatedStationWhenRemoval(lineName, preStationName, stationName)
        return sectionRepository.delete(request.toSection())
    }

    private fun validateExistingSection(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
        val line = Line.from(lineName)
        val preStation = Station.from(preStationName)
        val station = Station.from(stationName)

        require(sectionRepository.countByLine(line) > MIM_SECTION_SIZE) { INVALID_SECTION_SIZE_MESSAGE }
        require(sectionRepository.existsByLineAndPreStationAndStation(line, preStation, station)) {
            NOT_EXISTS_SECTION
        }
    }

    private fun modifySectionAssociatedPreStationWhenRemoval(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
    }

    private fun modifySectionAssociatedStationWhenRemoval(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
    }

    companion object {
        private const val MIM_SECTION_SIZE = 1
    }
}
