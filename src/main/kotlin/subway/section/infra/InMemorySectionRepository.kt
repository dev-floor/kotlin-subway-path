package subway.section.infra

import subway.section.domain.Section
import subway.section.domain.SectionRepository

class InMemorySectionRepository : SectionRepository {
    override fun findAll() = SECTIONS.toList()

    override fun save(section: Section) {
        SECTIONS.add(section)
    }

    override fun saveAll(vararg sections: Section) {
        SECTIONS.addAll(sections)
    }

    companion object {
        private val SECTIONS = mutableListOf<Section>()
    }
}
