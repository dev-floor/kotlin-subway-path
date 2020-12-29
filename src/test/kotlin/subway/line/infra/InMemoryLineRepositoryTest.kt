package subway.line.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import subway.line.domain.Line
import subway.line.domain.LineRepository

@Suppress("NonAsciiCharacters")
internal class InMemoryLineRepositoryTest {
    private lateinit var lineRepository: LineRepository

    @BeforeEach
    internal fun setUp() {
        lineRepository = InMemoryLineRepository().apply { saveAll(LINE_FIXTURES) }
    }

    @Test
    internal fun `findAll_InMemoryLineRepository의 모든 데이터를 조회`() {
        // when
        val lines = lineRepository.findAll()

        // then
        assertThat(lines).hasSize(LINE_FIXTURES.size)
    }

    @Test
    internal fun `save_InMemoryLineRepository에 데이터를 저장`() {
        // given
        val line = Line("테스트호선4")

        // when
        lineRepository.save(line)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll_InMemoryLineRepository에 여러 데이터를 저장`() {
        // given
        val line1 = Line("테스트노선4")
        val line2 = Line("테스트노선5")

        // when
        lineRepository.saveAll(line1, line2)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll_InMemoryLineRepository에 리스트로 된 데이터를 저장`() {
        // given
        val lines = listOf(Line("테스트노선4"), Line("테스트노선5"))

        // when
        lineRepository.saveAll(lines)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size + 2)
    }

    @Test
    internal fun `InMemoryLineRepository의 데이터를 노선의 이름으로 삭제`() {
        // given
        val name = "테스트노선1"

        // when
        lineRepository.deleteByName(name)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size - 1)
    }

    companion object {
        private val LINE_FIXTURES = listOf(
            Line("테스트노선1"),
            Line("테스트노선2"),
            Line("테스트노선3")
        )
    }
}
