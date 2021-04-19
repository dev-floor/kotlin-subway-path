package subway.dto

data class PathResponse(
    val time: Int,
    val distance: Int,
    val route: List<String>
)
