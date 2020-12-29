package subway.line.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import subway.line.infra.InMemoryLineRepository

@Suppress("NonAsciiCharacters")
internal class LineInitializerTest {
    @Test
    internal fun `initialize_초기 설정에 필요한 호선을 등록`() {
        // given
        val repository = InMemoryLineRepository()

        // when
        LineInitializer.initialize(repository)

        // then
        assertThat(repository.findAll()).hasSize(3)
    }
}
