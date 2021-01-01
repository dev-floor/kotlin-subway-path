package subway.path.domain

import subway.common.exception.INVALID_PATH_TYPE_MESSAGE
import subway.section.domain.Section

enum class PathType(
    private val weight: (Section) -> Long,
    private val subweight: (Section) -> Long,
) {
    DISTANCE(Section::distance, Section::duration),
    DURATION(Section::duration, Section::distance);

    companion object {
        fun of(type: String) = values().find { it.name == type }
            ?: throw IllegalArgumentException(INVALID_PATH_TYPE_MESSAGE)
    }
}
