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
    val upStationName: String,
    val downStationName: String,
    val distance: Long,
    val duration: Long,
) {
    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(upStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(downStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }

    fun toSection() = Section(
        line = Line.from(lineName),
        preStation = Station.from(upStationName),
        station = Station.from(downStationName),
        distance = distance,
        duration = duration
    )
}
