package subway.section.domain

import subway.line.domain.Line
import subway.station.domain.Station

interface SectionRepository {
    fun save(section: Section)

    fun saveAll(vararg sections: Section)

    fun saveAll(sections: List<Section>)

    fun findByLineAndPreStation(line: Line, preStation: Station): Section?

    fun findByLineAndStation(line: Line, station: Station): Section?

    fun findAll(): List<Section>

    fun existsByPreStation(preStation: Station): Boolean

    fun existsByStation(station: Station): Boolean

    fun existsByLineAndPreStation(line: Line, preStation: Station): Boolean

    fun existsByLineAndStation(line: Line, station: Station): Boolean

    fun existsByLineAndPreStationAndStation(
        line: Line,
        preStation: Station,
        station: Station,
    ): Boolean

    fun delete(section: Section): Boolean
}
