package subway.repository

import subway.domain.Section

object SectionRepository {
    private val sections = mutableListOf<Section>()

    private fun sections() = sections.toList()

    fun add(section: Section) = sections.add(section)

    fun findAll() = sections()

    fun findByLineNameAndUpwardName(lineName: String, stationName: String) = sections()
        .first { it.matchLineAndUpward(lineName, stationName) }

    fun findByLineNameAndDownwardName(lineName: String, stationName: String) = sections()
        .first { it.matchLineAndDownward(lineName, stationName) }

    fun existsByLineNameAndDownwardName(lineName: String, stationName: String): Boolean = sections()
        .any { it.matchLineAndDownward(lineName, stationName) }

    fun existsByLineNameAndUpwardName(lineName: String, stationName: String): Boolean = sections()
        .any { it.matchLineAndUpward(lineName, stationName) }

    fun delete(lineName: String, upwardName: String, downwardName: String) = sections
        .removeIf { it.match(lineName, upwardName, downwardName) }
}
