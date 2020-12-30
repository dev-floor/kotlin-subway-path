package subway.section.presentation

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
    val upStationName: String,
    val downStationName: String,
    val distance: Long,
    val duration: Long,
) {
    val line: Line
        get() = Line.from(lineName)

    val upStation: Station
        get() = Station.from(upStationName)

    val downStation: Station
        get() = Station.from(downStationName)

    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(upStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(downStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }

    fun toSection() = Section(
        line = Line.from(lineName),
        upStation = Station.from(upStationName),
        downStation = Station.from(downStationName),
        distance = distance,
        duration = duration
    )
}
