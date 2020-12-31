package subway.section.application

import subway.common.domain.Name
import subway.common.exception.INVALID_DISTANCE_MESSAGE
import subway.common.exception.INVALID_DURATION_MESSAGE
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.common.utils.isPositive
import subway.line.domain.Line
import subway.section.domain.Section
import subway.station.domain.Station

data class SectionRegisterRequest(
    val lineName: String,
    val preStationName: String,
    val stationName: String,
    val distance: Long,
    val duration: Long,
) {
    val line: Line
        get() = Line.from(lineName)

    val preStation: Station
        get() = Station.valueOf(preStationName)

    val station: Station
        get() = Station.valueOf(stationName)

    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(stationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }

    fun toSection() = Section(
        line = Line.from(lineName),
        preStation = Station.valueOf(preStationName),
        station = Station.valueOf(stationName),
        distance = distance,
        duration = duration
    )
}
