package subway.section.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import subway.section.infra.InMemorySectionRepository

@Suppress("NonAsciiCharacters")
internal class SectionInitializerTest {
    @Test
    internal fun `initialize() - 초기 설정에 필요한 구간을 등록`() {
        // given
        val repository = InMemorySectionRepository()

        // when
        SectionInitializer.initialize(repository)

        // then
        assertThat(repository.findAll()).hasSize(7)
    }
}
