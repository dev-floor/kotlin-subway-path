package subway.station.application

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import subway.section.infra.InMemorySectionRepository
import subway.station.exception.IllegalStationException
import subway.station.infra.InMemoryStationRepository
import subway.station.presentation.StationRegisterRequest
import subway.station.presentation.StationRemoveRequest

@Suppress("NonAsciiCharacters")
internal class StationServiceTest {
    private val stationRepository = InMemoryStationRepository()
    private val sectionRepository = InMemorySectionRepository()
    private lateinit var stationService: StationService

    @BeforeEach
    internal fun setUp() {
        stationService = StationService(stationRepository, sectionRepository)
    }

    @Test
    internal fun `register_역의 이름을 입력받아 역을 생성`() {
        // given
        val request = StationRegisterRequest("테스트역1")

        // when
        stationService.register(request)

        // then
        assertThat(stationRepository.findAll()).hasSize(1)
    }

    @Test
    internal fun `register_이미 존재하는 역의 경우 예외 생성`() {
        // given
        val request = StationRegisterRequest("테스트역1")
        stationService.register(request)

        // then
        assertThatThrownBy { stationService.register(request) }
            .isInstanceOf(IllegalStationException::class.java)
    }

    @Test
    internal fun `showAll_저장된 모든 역을 조회`() {
        // given
        stationService.register(StationRegisterRequest("테스트역1"))
        stationService.register(StationRegisterRequest("테스트역2"))

        // when
        val stations = stationService.showAll()

        // then
        assertThat(stations).hasSize(2)
    }

    @Test
    internal fun `remove_역의 이름에 해당하는 역을 삭제`() {
        // given
        val name = "테스트역1"
        stationService.register(StationRegisterRequest(name))

        // when
        val actual = stationService.remove(StationRemoveRequest(name))

        // then
        assertAll(
            { assertThat(actual).isTrue },
            { assertThat(stationRepository.findAll()).hasSize(0) }
        )
    }

    @AfterEach
    internal fun tearDown() {
        stationRepository.deleteAll()
        sectionRepository.deleteAll()
    }
}
