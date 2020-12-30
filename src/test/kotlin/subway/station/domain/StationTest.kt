package subway.station.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
internal class StationTest {
    @Test
    internal fun `match() - 해당하는 이름과 동일한 이름인지 확인`() {
        // given
        val name = "테스트노선1"
        val station = Station.from(name)

        // when
        val actual = station.match(name)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `from() - 해당하는 이름의 인스턴스 생성`() {
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
