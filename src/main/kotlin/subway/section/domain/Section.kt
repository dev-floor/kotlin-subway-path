package subway.section.domain

import subway.common.exception.INVALID_DISTANCE_MESSAGE
import subway.common.exception.INVALID_DURATION_MESSAGE
import subway.common.utils.isPositive
import subway.line.domain.Line
import subway.station.domain.Station

data class Section(
    val line: Line,
    val upStation: Station,
    val downStation: Station,
    val distance: Long,
    val duration: Long,
) {
    init {
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }
}
