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
        val (lineName, preStationName, stationName, _, _) = request

        validateExistingLineAndStations(lineName, preStationName, stationName)
        validateNotExistingSection(lineName, preStationName, stationName)
        validateNotCycledSection(lineName, preStationName, stationName)

        modifySectionAssociatedPreStationWhenRegistration(lineName, preStationName, stationName)
        modifySectionAssociatedStationWhenRegistration(lineName, preStationName, stationName)
        sectionRepository.save(request.toSection())
    }

    private fun validateExistingLineAndStations(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
        require(lineRepository.existsByName(lineName)) { NOT_EXISTS_LINE }
        require(stationRepository.existsByName(preStationName)) { NOT_EXISTS_STATION }
        require(stationRepository.existsByName(stationName)) { NOT_EXISTS_STATION }
    }

    private fun validateNotExistingSection(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
        val line = Line.from(lineName)
        val preStation = Station.from(preStationName)
        val station = Station.from(stationName)

        require(!sectionRepository.existsByLineAndPreStationAndStation(line, preStation, station)) {
            ALREADY_EXISTS_SECTION
        }
        require(!sectionRepository.existsByLineAndPreStation(line, preStation)
                || !sectionRepository.existsByLineAndStation(line, station)) {
            INVALID_SECTION_MESSAGE
        }
    }

    private fun validateNotCycledSection(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) {
        val line = Line.from(lineName)
        val preStation = Station.from(preStationName)
        val station = Station.from(stationName)

        require(!sectionRepository.existsByLineAndPreStation(line, station)
                || !sectionRepository.existsByLineAndStation(line, preStation)) {
            INVALID_SECTION_MESSAGE
        }
    }

    private fun modifySectionAssociatedPreStationWhenRegistration(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) = sectionRepository.findByLineAndPreStation(Line.from(lineName), Station.from(preStationName))
        ?.let {
            sectionRepository.delete(it)
            sectionRepository.save(Section(it.line, Station.from(stationName), it.station))
        }


    private fun modifySectionAssociatedStationWhenRegistration(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) = sectionRepository.findByLineAndStation(Line.from(lineName), Station.from(stationName))
        ?.let {
            sectionRepository.delete(it)
            sectionRepository.save(Section(it.line, it.preStation, Station.from(preStationName)))
        }

    fun remove(request: SectionRemoveRequest): Boolean {
        val (lineName, preStationName, stationName) = request

        validateExistingLineAndStations(lineName, preStationName, stationName)
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
