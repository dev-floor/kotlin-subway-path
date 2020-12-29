package subway.line.domain

import subway.common.domain.SubwayInitializer

@Suppress("NonAsciiCharacters")
object LineInitializer : SubwayInitializer<LineRepository> {
    val 이호선 = Line("2호선")
    val 삼호선 = Line("3호선")
    val 신분당선 = Line("신분당선")

    override fun initialize(repository: LineRepository) = repository.saveAll(이호선, 삼호선, 신분당선)
}
