package subway.path.application

import subway.path.domain.PathType
import subway.path.domain.PathType.DISTANCE
import subway.path.domain.PathType.DURATION
import subway.station.domain.Station

data class PathResponse(val stations: List<String>, val distance: Long, val duration: Long) {
    companion object {
        fun of(
            pathType: PathType,
            stations: List<Station>,
            weight: Double,
            subWeight: Double,
        ) = when (pathType) {
            DISTANCE -> PathResponse(
                stations.map { it.name.name },
                weight.toLong(),
                subWeight.toLong()
            )
            DURATION -> PathResponse(
                stations.map { it.name.name },
                subWeight.toLong(),
                weight.toLong(),
            )
        }
    }
}
