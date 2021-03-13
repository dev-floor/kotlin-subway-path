package subway.repository

import subway.domain.Section

object SectionRepository {
    private val sections = mutableListOf<Section>()

    private fun sections() = sections.toList()

    fun add(section: Section) = sections.add(section)

    fun findAll() = sections()

    fun findByLineNameAndUpwardName(lineName: String, stationName: String) = sections()
        .first { it.upwardStation.match(stationName) && it.line.match(lineName) }

    fun findByLineNameAndDownwardName(lineName: String, stationName: String) = sections()
        .first { it.downwardStation.match(stationName) && it.line.match(lineName) }

    fun existsByLineNameAndDownwardName(lineName: String, stationName: String): Boolean = sections()
        .any { it.downwardStation.match(stationName) && it.line.match(lineName) }

    fun existsByLineNameAndUpwardName(lineName: String, stationName: String): Boolean = sections()
        .any { it.upwardStation.match(stationName) && it.line.match(lineName) }

    fun delete(lineName: String, upwardName: String, downwardName: String) = sections
        .removeIf {
            it.line.match(lineName) &&
                it.upwardStation.match(upwardName) &&
                it.downwardStation.match(downwardName)
        }
}
