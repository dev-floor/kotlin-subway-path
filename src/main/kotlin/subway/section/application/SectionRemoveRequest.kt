package subway.section.application

import subway.common.domain.Name
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.line.domain.Line
import subway.section.domain.Section
import subway.station.domain.Station

data class SectionRemoveRequest(
    val lineName: String,
    val preStationName: String,
    val stationName: String,
) {
    val line get() = Line.from(lineName)

    val preStation get() = Station.valueOf(preStationName)

    val station get() = Station.valueOf(stationName)

    val section
        get() = Section(
            line = Line.from(lineName),
            preStation = Station.valueOf(preStationName),
            station = Station.valueOf(stationName),
        )

    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(stationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }
}
