package subway.section.infra

import subway.section.domain.Section
import subway.section.domain.SectionRepository

class InMemorySectionRepository : SectionRepository {
    private val sections = mutableListOf<Section>()

    override fun findAll() = sections.toList()

    override fun save(section: Section) {
        sections.add(section)
    }

    override fun saveAll(vararg sections: Section) {
        this.sections.addAll(sections)
    }

    override fun saveAll(sections: List<Section>) {
        this.sections.addAll(sections)
    }
}
