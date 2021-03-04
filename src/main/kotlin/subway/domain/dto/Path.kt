package subway.domain.dto

data class Path(
    val time: Int = 0,
    val distance: Int = 0,
    val route: List<String> = listOf()
)
