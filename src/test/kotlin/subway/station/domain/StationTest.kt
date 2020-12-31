package subway.station.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
internal class StationTest {
    @Test
    internal fun `match() - 해당하는 이름과 동일한 이름인지 확인`() {
        // given
        val name = "테스트노선1"
        val station = Station.valueOf(name)

        // when
        val actual = station.match(name)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `isUpwardEndStation() - 상행 종점에 해당하는 역인지 여부를 확인`() {
        // given
        val station = Station.UPWARD_END_STATION

        // when
        val actual = station.isUpwardEndStation()

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `from() - 해당하는 이름의 인스턴스 생성`() {
        // given
        val name = "테스트노선1"

        // when
        val station = Station.valueOf(name)

        // then
        assertThat(station).satisfies {
            assertThat(it).isNotNull
            assertThat(it.name.name).isEqualTo(name)
        }
    }
}
