package subway.dto

data class SectionRegisterRequest(
    val lineName: String,
    val upwardStationName: String,
    val downwardStationName: String,
    var time: Int,
    var distance: Int
)
