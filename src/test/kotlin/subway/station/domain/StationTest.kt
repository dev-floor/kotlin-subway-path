package subway.station.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
internal class StationTest {
    @Test
    internal fun `from_이름을 입력받아 인스턴스 생성`() {
        // given
        val name = "테스트노선1"

        // when
        val station = Station.from(name)

        // then
        assertThat(station).satisfies {
            assertThat(it).isNotNull
            assertThat(it.name.name).isEqualTo(name)
        }
    }
}
