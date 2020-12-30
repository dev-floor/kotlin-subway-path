package subway.section.domain

import subway.station.domain.Station

interface SectionRepository {
    fun save(section: Section)

    fun saveAll(vararg sections: Section)

    fun saveAll(sections: List<Section>)

    fun findAll(): List<Section>

    fun existsByUpStation(upStation: Station): Boolean

    fun existsByDownStation(downStation: Station): Boolean

    fun deleteAll()
}
