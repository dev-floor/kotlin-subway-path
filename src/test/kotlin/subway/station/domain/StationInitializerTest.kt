package subway.station.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import subway.station.infra.InMemoryStationRepository

@Suppress("NonAsciiCharacters")
internal class StationInitializerTest {
    @Test
    internal fun `initialize() - 초기 설정에 필요한 역을 등록`() {
        // given
        val repository = InMemoryStationRepository()

        // when
        StationInitializer.initialize(repository)

        // then
        assertThat(repository.findAll()).hasSize(7)
    }
}
