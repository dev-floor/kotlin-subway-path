package subway.section.infra

import subway.section.domain.Section
import subway.section.domain.SectionRepository
import subway.station.domain.Station

class InMemorySectionRepository : SectionRepository {
    private val sections: MutableList<Section> = mutableListOf()

    override fun save(section: Section) {
        sections.add(section)
    }

    override fun saveAll(vararg sections: Section) {
        this.sections.addAll(sections)
    }

    override fun saveAll(sections: List<Section>) {
        this.sections.addAll(sections)
    }

    override fun findAll() = sections.toList()

    override fun existsByUpStation(upStation: Station) = sections.any { it.upStation == upStation }

    override fun existsByDownStation(downStation: Station) =
        sections.any { it.downStation == downStation }

    override fun deleteAll() = sections.clear()
}
