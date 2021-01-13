package subway.repository

import subway.domain.Section

object SectionRepository {
    private val sections = mutableListOf<Section>()

    fun sections() = sections.toList()

    fun addSection(section: Section) {
        sections.add(section)
    }

//    fun deleteSectionByName(name: String) = sections.removeIf { it.name == name }
}
