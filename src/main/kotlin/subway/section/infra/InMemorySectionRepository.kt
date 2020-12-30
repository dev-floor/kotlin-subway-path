package subway.section.infra

import subway.line.domain.Line
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

    override fun findByLineAndPreStation(line: Line, preStation: Station) =
        sections.filter { it.line == line }
            .find { it.preStation == preStation }

    override fun findByLineAndStation(line: Line, station: Station) =
        sections.filter { it.line == line }
            .find { it.station == station }

    override fun findAll() = sections.toList()

    override fun existsByPreStation(preStation: Station) =
        sections.any { it.preStation == preStation }

    override fun existsByStation(station: Station) =
        sections.any { it.station == station }

    override fun existsByLineAndPreStationAndStation(
        line: Line,
        preStation: Station,
        station: Station,
    ) = sections.any { it.match(line, preStation, station) }

    override fun delete(section: Section) = sections.removeIf { it == section }
}
