package subway.path.domain

import subway.common.exception.INVALID_PATH_TYPE_MESSAGE
import subway.section.domain.Section

enum class PathType(val weight: (Section) -> Double, val subweight: (Section) -> Double) {
    DISTANCE({ it.distance.toDouble() }, { it.duration.toDouble() }),
    DURATION({ it.duration.toDouble() }, { it.distance.toDouble() });

    companion object {
        fun from(type: String) = values().find { it.name == type }
            ?: throw IllegalArgumentException(INVALID_PATH_TYPE_MESSAGE)
    }
}
