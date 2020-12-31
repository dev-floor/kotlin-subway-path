package subway.line.application

import subway.common.domain.Name
import subway.common.exception.INVALID_DISTANCE_MESSAGE
import subway.common.exception.INVALID_DURATION_MESSAGE
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.common.utils.isPositive

data class LineRegisterRequest(
    val lineName: String,
    val preStationName: String,
    val stationName: String,
    val distance: Long,
    val duration: Long,
) {
    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(preStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(stationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }
}
