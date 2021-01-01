package subway.common.application

import subway.line.domain.LineInitializer
import subway.line.infra.InMemoryLineRepository
import subway.section.domain.SectionInitializer
import subway.section.infra.InMemorySectionRepository
import subway.station.domain.StationInitializer
import subway.station.infra.InMemoryStationRepository

object RepositoryHandler {
    val STATION_REPOSITORY = InMemoryStationRepository().also { StationInitializer.initialize(it) }
    val LINE_REPOSITORY = InMemoryLineRepository().also { LineInitializer.initialize(it) }
    val SECTION_REPOSITORY = InMemorySectionRepository().also { SectionInitializer.initialize(it) }
}
