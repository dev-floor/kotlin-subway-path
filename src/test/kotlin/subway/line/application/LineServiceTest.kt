package subway.line.application

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.LineRepository
import subway.line.infra.InMemoryLineRepository
import subway.section.domain.SectionRepository
import subway.section.infra.InMemorySectionRepository
import subway.station.domain.Station
import subway.station.domain.StationRepository
import subway.station.infra.InMemoryStationRepository

@Suppress("NonAsciiCharacters")
internal class LineServiceTest {
    private lateinit var stationRepository: StationRepository
    private lateinit var lineRepository: LineRepository
    private lateinit var sectionRepository: SectionRepository
    private lateinit var lineService: LineService

    @BeforeEach
    internal fun setUp() {
        stationRepository = InMemoryStationRepository()
        lineRepository = InMemoryLineRepository()
        sectionRepository = InMemorySectionRepository()
        lineService = LineService(stationRepository, lineRepository, sectionRepository)
    }

    @Test
    internal fun `register() - 해당하는 노선을 등록`() {
        // given
        stationRepository.saveAll(
            Station.UPWARD_END_STATION,
            Station.valueOf("테스트역1"),
            Station.valueOf("테스트역2")
        )

        val request = LineRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)

        // when
        lineService.register(request)

        // then
        assertAll(
            { assertThat(lineRepository.existsByName("테스트노선1")).isTrue },
            { assertThat(sectionRepository.findAll()).hasSize(2) }
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["테스트역1", "테스트역2"])
    internal fun `register() - 역이 존재하지 않는 경우 예외 발생`(name: String) {
        // given
        stationRepository.saveAll(
            Station.UPWARD_END_STATION,
            Station.valueOf(name),
        )

        val request = LineRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)

        // then
        assertThatIllegalArgumentException().isThrownBy { lineService.register(request) }
            .withMessage(NOT_EXISTS_STATION)
    }
}
