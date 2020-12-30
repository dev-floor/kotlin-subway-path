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
    val distance: Long = INITIAL_DISTANCE,
    val duration: Long = INITIAL_DURATION,
) {
    init {
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }

    fun match(line: Line, upStation: Station, downStation: Station) =
        this.line == line && this.upStation == upStation && this.downStation == downStation

    companion object {
        const val INITIAL_DISTANCE = 2L
        const val INITIAL_DURATION = 2L

        fun from(
            lineName: String,
            upStationName: String,
            downStationName: String,
            distance: Long = INITIAL_DISTANCE,
            duration: Long = INITIAL_DURATION,
        ) = Section(
            line = Line.from(lineName),
            upStation = Station.from(upStationName),
            downStation = Station.from(downStationName),
            distance = distance,
            duration = duration
        )
    }
}
