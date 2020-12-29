package subway.line.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
internal class LineTest {
    @Test
    internal fun `from_이름을 입력받아 인스턴스 생성`() {
        // given
        val name = "테스트노선1"

        // when
        val line = Line.from(name)

        // then
        assertThat(line).satisfies {
            assertThat(it).isNotNull
            assertThat(it.name.name).isEqualTo(name)
        }
    }
}
