package subway.line.application

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import subway.common.exception.NOT_EXISTS_STATION
import subway.line.domain.Line
import subway.line.domain.LineRepository
import subway.line.infra.InMemoryLineRepository
import subway.section.domain.Section
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
            {
                assertThat(
                    sectionRepository
                        .existsByLineAndPreStationAndStation(
                            Line.from("테스트노선1"),
                            Station.UPWARD_END_STATION,
                            Station.valueOf("테스트역1")
                        )
                )
                    .isTrue
            },
            {
                assertThat(
                    sectionRepository
                        .existsByLineAndPreStationAndStation(
                            Line.from("테스트노선1"),
                            Station.valueOf("테스트역1"),
                            Station.valueOf("테스트역2")
                        )
                )
                    .isTrue
            }
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

    @Test
    internal fun `findAll() - 저장된 역을 이름 순서대로 조회`() {
        // given
        stationRepository.saveAll(
            Station.UPWARD_END_STATION,
            Station.valueOf("테스트역1"),
            Station.valueOf("테스트역2")
        )
        lineService.register(LineRegisterRequest("테스트노선3", "테스트역1", "테스트역2", 2, 3))
        lineService.register(LineRegisterRequest("테스트노선2", "테스트역1", "테스트역2", 2, 3))
        lineService.register(LineRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3))

        // when
        val lines = lineService.showAll()

        // then
        assertThat(lines)
            .isEqualTo(
                listOf(
                    Line.from("테스트노선1"),
                    Line.from("테스트노선2"),
                    Line.from("테스트노선3")
                )
            )
    }

    @Test
    internal fun `remove() - 해당하는 노선을 삭제`() {
        // given
        stationRepository.saveAll(
            Station.UPWARD_END_STATION,
            Station.valueOf("테스트역1"),
            Station.valueOf("테스트역2"),
            Station.valueOf("테스트역3"),
        )
        lineRepository.saveAll(Line.from("테스트노선1"), Line.from("테스트노선2"))
        sectionRepository.saveAll(
            Section.ofUpwardEnd("테스트노선1", "테스트역1"),
            Section.of("테스트노선1", "테스트역1", "테스트역2", 2, 3),
            Section.of("테스트노선1", "테스트역2", "테스트역3", 2, 3),
            Section.ofUpwardEnd("테스트노선2", "테스트역2"),
            Section.of("테스트노선2", "테스트역2", "테스트역3", 2, 3),
        )

        val request = LineRemoveRequest("테스트노선1")

        // when
        lineService.remove(request)

        // then
        assertAll(
            { assertThat(lineRepository.existsByName("테스트노선1")).isFalse },
            { assertThat(sectionRepository.findAllByLine(Line.from("테스트노선1"))).hasSize(0) }
        )
    }
}
