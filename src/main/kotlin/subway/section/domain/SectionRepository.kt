package subway.section.domain

import subway.line.domain.Line
import subway.station.domain.Station

interface SectionRepository {
    fun save(section: Section)

    fun saveAll(vararg sections: Section)

    fun saveAll(sections: List<Section>)

    fun findByLineAndUpStation(line: Line, upStation: Station): Section?

    fun findByLineAndDownStation(line: Line, downStation: Station): Section?

    fun findAll(): List<Section>

    fun existsByUpStation(upStation: Station): Boolean

    fun existsByDownStation(downStation: Station): Boolean

    fun existsByLineAndUpStationAndDownStation(
        line: Line,
        upStation: Station,
        downStation: Station,
    ): Boolean

    fun delete(section: Section): Boolean

    fun deleteAll()
}
