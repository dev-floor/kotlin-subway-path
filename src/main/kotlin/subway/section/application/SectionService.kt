package subway.section.application

import subway.common.exception.ALREADY_EXISTS_SECTION
import subway.common.exception.NOT_EXISTS_LINE
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

        validate(lineName, preStationName, stationName)
        modifyRegisteredSectionAssociatedWithPreStation(lineName, preStationName, stationName)
        modifyRegisteredSectionAssociatedWithStation(lineName, preStationName, stationName)

        sectionRepository.save(request.toSection())
    }

    private fun validate(lineName: String, preStationName: String, stationName: String) {
        require(lineRepository.existsByName(lineName)) { NOT_EXISTS_LINE }
        require(stationRepository.existsByName(preStationName)) { NOT_EXISTS_STATION }
        require(stationRepository.existsByName(stationName)) { NOT_EXISTS_STATION }
        require(!sectionRepository.existsByLineAndPreStationAndStation(
            Line.from(lineName),
            Station.from(preStationName),
            Station.from(stationName)
        )) { ALREADY_EXISTS_SECTION }
    }

    private fun modifyRegisteredSectionAssociatedWithPreStation(
        lineName: String,
        preStationName: String,
        stationName: String,
    ) = sectionRepository.findByLineAndPreStation(Line.from(lineName), Station.from(preStationName))
        ?.let {
            sectionRepository.delete(it)
            sectionRepository.save(Section(it.line, Station.from(stationName), it.station))
        }


    private fun modifyRegisteredSectionAssociatedWithStation(
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

        validate(lineName, preStationName, stationName)
        return sectionRepository.delete(request.toSection())
    }
}
