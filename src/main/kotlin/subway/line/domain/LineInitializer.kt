package subway.line.domain

@Suppress("NonAsciiCharacters")
object LineInitializer {
    val 이호선 = Line("2호선")
    val 삼호선 = Line("3호선")
    val 신분당선 = Line("신분당선")

    fun initialize(lineRepository: LineRepository) = lineRepository.saveAll(이호선, 삼호선, 신분당선)
}
