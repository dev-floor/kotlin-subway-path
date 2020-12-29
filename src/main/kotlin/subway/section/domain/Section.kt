package subway.section.domain

import subway.line.domain.Line
import subway.station.domain.Station

class Section(
    val sequence: Long,
    val upStation: Station,
    val downStation: Station,
    val line: Line,
    val distance: Long,
    val duration: Long,
)
