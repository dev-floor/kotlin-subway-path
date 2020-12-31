package subway.section.domain

import subway.line.domain.Line
import subway.station.domain.Station

interface SectionRepository {
    fun save(section: Section)

    fun saveAll(vararg sections: Section)

    fun saveAll(sections: List<Section>)

    fun findByLineAndPreStation(line: Line, preStation: Station): Section?

    fun findByLineAndStation(line: Line, station: Station): Section?

    fun find(section: Section): Section?

    fun findAll(): List<Section>

    fun findAllByLine(line: Line): List<Section>

    fun countByLine(line: Line): Int

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

    fun deleteByLine(line: Line): Boolean
}
