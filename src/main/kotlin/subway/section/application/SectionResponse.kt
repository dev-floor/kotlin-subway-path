package subway.section.application

import subway.section.domain.Section

data class SectionResponse(
    val line: String,
    val preStation: String,
    val station: String,
    val distance: Long,
    val duration: Long,
) {
    companion object {
        fun from(section: Section) = SectionResponse(
            section.line.name.name,
            section.preStation.name.name,
            section.station.name.name,
            section.distance,
            section.duration,
        )
    }
}
