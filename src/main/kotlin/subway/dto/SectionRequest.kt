package subway.dto

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station

data class SectionRequest(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
    var time: Int? = Section.TIME_DEFAULT,
    var distance: Int? = Section.DISTANCE_DEFAULT
)
