package subway.common.application

import subway.common.application.RepositoryHandler.LINE_REPOSITORY
import subway.common.application.RepositoryHandler.SECTION_REPOSITORY
import subway.common.application.RepositoryHandler.STATION_REPOSITORY
import subway.line.application.LineService
import subway.section.application.SectionService
import subway.station.application.StationService

object ServiceHandler {
    val STATION_SERVICE = StationService(STATION_REPOSITORY, SECTION_REPOSITORY)
    val LINE_SERVICE = LineService(STATION_REPOSITORY, LINE_REPOSITORY, SECTION_REPOSITORY)
    val SECTION_SERVICE = SectionService(STATION_REPOSITORY, LINE_REPOSITORY, SECTION_REPOSITORY)
}
