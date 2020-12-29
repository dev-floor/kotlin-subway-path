package subway.section.domain

interface SectionRepository {
    fun findAll(): List<Section>

    fun save(section: Section)

    fun saveAll(vararg sections: Section)

    fun saveAll(sections: List<Section>)
}
