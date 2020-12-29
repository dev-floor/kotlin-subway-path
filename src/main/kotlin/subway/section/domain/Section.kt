package subway.section.domain

import subway.line.domain.Line
import subway.station.domain.Station

data class Section(
    val line: Line,
    val upStation: Station,
    val downStation: Station,
    val distance: Long,
    val duration: Long,
)
