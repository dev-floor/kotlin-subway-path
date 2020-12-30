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
    val line: Line
        get() = Line.from(lineName)

    val preStation: Station
        get() = Station.from(preStationName)

    val station: Station
        get() = Station.from(stationName)

    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(preStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(stationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }

    fun toSection() = Section(
        line = Line.from(lineName),
        preStation = Station.from(preStationName),
        station = Station.from(stationName),
    )
}
