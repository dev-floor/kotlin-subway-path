package subway.section.domain

import subway.common.exception.INVALID_DISTANCE_MESSAGE
import subway.common.exception.INVALID_DURATION_MESSAGE
import subway.common.utils.isPositive
import subway.line.domain.Line
import subway.station.domain.Station

class Section(
    val line: Line,
    val preStation: Station,
    val station: Station,
    val distance: Long = INITIAL_DISTANCE,
    val duration: Long = INITIAL_DURATION,
) {
    init {
        require(distance.isPositive) { INVALID_DISTANCE_MESSAGE }
        require(duration.isPositive) { INVALID_DURATION_MESSAGE }
    }

    fun match(line: Line, preStation: Station, station: Station) =
        this.line == line && this.preStation == preStation && this.station == station

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Section

        if (line != other.line) return false
        if (preStation != other.preStation) return false
        if (station != other.station) return false

        return true
    }

    override fun hashCode(): Int {
        var result = line.hashCode()
        result = 31 * result + preStation.hashCode()
        result = 31 * result + station.hashCode()
        return result
    }

    companion object {
        const val INITIAL_DISTANCE = 2L
        const val INITIAL_DURATION = 2L

        fun of(
            lineName: String,
            preStationName: String,
            stationName: String,
            distance: Long = INITIAL_DISTANCE,
            duration: Long = INITIAL_DURATION,
        ) = Section(
            line = Line.from(lineName),
            preStation = Station.from(preStationName),
            station = Station.from(stationName),
            distance = distance,
            duration = duration
        )

        fun ofUpwardEnd(
            lineName: String,
            stationName: String,
        ) = Section(
            line = Line.from(lineName),
            preStation = Station.UPWARD_END_STATION,
            station = Station.from(stationName),
            distance = 0,
            duration = 0
        )

        fun ofUpwardEnd(
            line: Line,
            station: Station,
        ) = Section(
            line = line,
            preStation = Station.UPWARD_END_STATION,
            station = station,
            distance = 0,
            duration = 0
        )
    }
}
