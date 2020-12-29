package subway.station.application

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import subway.station.exception.IllegalStationException
import subway.station.infra.InMemoryStationRepository
import subway.station.presentation.StationRegisterRequest

@Suppress("NonAsciiCharacters")
internal class StationServiceTest {
    @Test
    internal fun `register_역의 이름을 입력받아 역을 생성`() {
        // given
        val request = StationRegisterRequest("테스트역1")
        val stationService = StationService(InMemoryStationRepository())

        // when
        stationService.register(request)

        // then
        assertThat(stationService.showAll()).hasSize(1)
    }

    @Test
    internal fun `register_이미 존재하는 역의 경우 예외 생성`() {
        // given
        val request = StationRegisterRequest("테스트역1")
        val stationService = StationService(InMemoryStationRepository())
        stationService.register(request)

        // then
        assertThatThrownBy { stationService.register(request) }
            .isInstanceOf(IllegalStationException::class.java)
    }

    @Test
    internal fun `showAll_저장된 모든 역을 조회`() {
        // given
        val stationService = StationService(InMemoryStationRepository())
        stationService.register(StationRegisterRequest("테스트역1"))
        stationService.register(StationRegisterRequest("테스트역2"))

        // when
        val stations = stationService.showAll()

        // then
        assertThat(stations).hasSize(2)
    }
}
