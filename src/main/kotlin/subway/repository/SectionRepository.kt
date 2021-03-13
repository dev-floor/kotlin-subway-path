package subway.repository

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station

object SectionRepository {
    private val sections = mutableListOf<Section>()

    private fun sections() = sections.toList()

    fun add(section: Section) = sections.add(section)

    fun findAll() = sections()

    fun findByLineNameAndUpwardName(line: Line, station: Station) = sections()
        .first { it.upwardStation.name == station.name && it.line.name == line.name }

    fun findByLineNameAndDownwardName(line: Line, station: Station) = sections()
        .first { it.downwardStation.name == station.name && it.line.name == line.name }

    fun existsByLineNameAndDownwardName(line: Line, station: Station): Boolean = sections()
        .any { it.downwardStation.name == station.name && it.line.name == line.name }

    fun existsByLineNameAndUpwardName(line: Line, station: Station): Boolean = sections()
        .any { it.upwardStation.name == station.name && it.line.name == line.name }

    fun delete(line: Line, upward: Station, downward: Station) = sections
        .removeIf {
            it.line.name == line.name &&
                it.upwardStation.name == upward.name &&
                it.downwardStation.name == downward.name
        }
}
