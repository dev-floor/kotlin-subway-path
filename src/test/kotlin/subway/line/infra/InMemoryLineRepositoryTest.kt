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
    internal fun `save() - 해당 노선을 저장`() {
        // given
        val line = Line.from("테스트호선4")

        // when
        lineRepository.save(line)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll() - vararg 타입의 여러 노선들을 저장`() {
        // given
        val line1 = Line.from("테스트노선4")
        val line2 = Line.from("테스트노선5")

        // when
        lineRepository.saveAll(line1, line2)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll() - list 타입의 여러 노선들을 저장`() {
        // given
        val lines = listOf(Line.from("테스트노선4"), Line.from("테스트노선5"))

        // when
        lineRepository.saveAll(lines)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size + 2)
    }

    @Test
    internal fun `findAll() - 모든 노선들을 조회`() {
        // when
        val lines = lineRepository.findAll()

        // then
        assertThat(lines).hasSize(LINE_FIXTURES.size)
    }

    @Test
    internal fun `exists() - 해당하는 노선이 존재하는지 여부를 확인`() {
        // given
        val line = Line.from("테스트노선1")

        // when
        val actual = lineRepository.exists(line)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `existsByName() - 해당하는 이름의 노선이 존재하는지 여부를 확인`() {
        // given
        val name = "테스트노선1"

        // when
        val actual = lineRepository.existsByName(name)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `deleteByName() - 해당하는 이름의 노선을 삭제`() {
        // given
        val name = "테스트노선1"

        // when
        lineRepository.deleteByName(name)

        // then
        assertThat(lineRepository.findAll()).hasSize(LINE_FIXTURES.size - 1)
    }

    companion object {
        private val LINE_FIXTURES = listOf(
            Line.from("테스트노선1"),
            Line.from("테스트노선2"),
            Line.from("테스트노선3")
        )
    }
}
