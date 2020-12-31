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
        sectionRepository.save(request.section)
    }

    private fun validateLineAndStations(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
        require(lineRepository.existsByName(lineName)) { NOT_EXISTS_LINE }
        require(preStationName.isBlank() || stationRepository.existsByName(preStationName)) {
            NOT_EXISTS_STATION
        }
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
    ) = require(
        !sectionRepository.existsByLineAndPreStation(line, station) ||
                !sectionRepository.existsByLineAndStation(line, preStation)
    ) {
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
        validateLineAndStations(request.lineName, request.preStationName, request.stationName)
        validateSectionToRemove(request.line, request.preStation, request.station)

        modifySectionAssociatedWithRemoval(request)

        return sectionRepository.delete(request.section)
    }

    private fun validateSectionToRemove(
        line: Line,
        preStation: Station,
        station: Station,
    ) {
        require(sectionRepository.countByLine(line) > MIM_SECTION_SIZE) { INVALID_SECTION_SIZE_MESSAGE }
        require(sectionRepository.existsByLineAndPreStationAndStation(line, preStation, station)) {
            NOT_EXISTS_SECTION
        }
    }

    private fun modifySectionAssociatedWithRemoval(request: SectionRemoveRequest) {
        val removalSection = sectionRepository.find(request.section) ?: throw AssertionError()

        sectionRepository.findByLineAndPreStation(request.line, request.station)
            ?.let {
                sectionRepository.delete(it)
                sectionRepository.save(setUpRegisteringSection(removalSection, it))
            }
    }

    private fun setUpRegisteringSection(
        removalSection: Section,
        associatedSection: Section,
    ): Section {
        if (removalSection.preStation.isUpwardEndStation()) {
            return Section.ofUpwardEnd(removalSection.line, associatedSection.station)
        }
        return Section(
            removalSection.line,
            removalSection.preStation,
            associatedSection.station,
            removalSection.distance + associatedSection.distance,
            removalSection.duration + associatedSection.duration
        )
    }

    companion object {
        private const val MIM_SECTION_SIZE = 2
    }
}
