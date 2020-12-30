package subway.section.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import subway.line.domain.Line
import subway.station.domain.Station

internal class SectionTest {
    @Test
    internal fun `match() - 노선,이전역,현재역이 동일한지 여부를 확인`() {
        // given
        val line = Line.from("테스트노선1")
        val preStation = Station.from("테스트역1")
        val station = Station.from("테스트역2")
        val section = Section(line, preStation, station)

        // when
        val actual = section.match(line, preStation, station)

        // then
        assertThat(actual).isTrue
    }
}
