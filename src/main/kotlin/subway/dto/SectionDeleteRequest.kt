package subway.dto

data class SectionDeleteRequest(
    val lineName: String,
    val upwardStationName: String,
    val downwardStationName: String
)
