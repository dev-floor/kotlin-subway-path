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
    val line get() = Line.from(lineName)

    val preStation get() = Station.valueOf(preStationName)

    val station get() = Station.valueOf(stationName)

    val section
        get() = Section(
            line = Line.from(lineName),
            preStation = Station.valueOf(preStationName),
            station = Station.valueOf(stationName),
            distance = distance,
            duration = duration
        )

    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(stationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }

    companion object {
        fun of(
            lineName: String,
            preStationName: String,
            stationName: String,
            distance: String,
            duration: String,
        ) = SectionRegisterRequest(
            lineName = lineName,
            preStationName = preStationName,
            stationName = stationName,
            distance = distance.toLong(),
            duration = duration.toLong()
        )
    }
}
