package subway.line.infra

import subway.line.domain.Line

@Suppress("NonAsciiCharacters")
object LineRepositoryFactory {
    val 이호선 = Line("2호선")
    val 삼호선 = Line("3호선")
    val 신분당선 = Line("신분당선")

    fun create() = InMemoryLineRepository().apply {
        saveAll(이호선, 삼호선, 신분당선)
    }
}
