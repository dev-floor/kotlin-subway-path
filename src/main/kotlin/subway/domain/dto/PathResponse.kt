package subway.domain.dto

data class PathResponse(
    val time: Int = 0,
    val distance: Int = 0,
    val route: List<String> = listOf()
)
