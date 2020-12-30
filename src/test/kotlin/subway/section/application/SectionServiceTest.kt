package subway.section.application

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
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
internal class SectionServiceTest {
    private lateinit var stationRepository: StationRepository
    private lateinit var lineRepository: LineRepository
    private lateinit var sectionRepository: SectionRepository
    private lateinit var sectionService: SectionService

    @BeforeEach
    internal fun setUp() {
        stationRepository = InMemoryStationRepository()
        lineRepository = InMemoryLineRepository()
        sectionRepository = InMemorySectionRepository()
        sectionService = SectionService(stationRepository, lineRepository, sectionRepository)
    }

    @Test
    internal fun `register() - 해당하는 구간을 등록`() {
        // given
        lineRepository.save(Line.from("테스트노선1"))
        stationRepository.saveAll(Station.from("테스트역1"), Station.from("테스트역2"))

        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)

        // when
        sectionService.register(request)

        // then
        assertThat(sectionRepository.findAll()).hasSize(1)
    }

    @Test
    internal fun `register() - 존재하지 않는 노선일 경우 예외 발생`() {
        // given
        stationRepository.saveAll(Station.from("테스트역1"), Station.from("테스트역2"))

        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)

        // then
        assertThatIllegalArgumentException().isThrownBy { sectionService.register(request) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["테스트역1", "테스트역2"])
    internal fun `register() - 존재하지 않는 역일 경우 예외 발생`(stationName: String) {
        // given
        lineRepository.save(Line.from("테스트노선1"))
        stationRepository.save(Station.from(stationName))

        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)

        // then
        assertThatIllegalArgumentException().isThrownBy { sectionService.register(request) }
    }

    @Test
    internal fun `register() - 기존에 존재하는 구간의 이전역과 연관된 구간을 추가할 경우`() {
        // given
        val line = Line.from("테스트노선1")
        val station1 = Station.from("테스트역1")
        val station2 = Station.from("테스트역2")
        val station3 = Station.from("테스트역3")
        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)

        lineRepository.save(line)
        stationRepository.saveAll(station1, station2, station3)
        sectionRepository.save(Section.from("테스트노선1", "테스트역1", "테스트역3", 2, 3))

        // when
        sectionService.register(request)

        // then
        assertThat(sectionRepository).satisfies {
            assertThat(it.findAll()).hasSize(2)
            assertThat(it.existsByLineAndPreStationAndStation(line, station1, station2)).isTrue
            assertThat(it.existsByLineAndPreStationAndStation(line, station2, station3)).isTrue
        }
    }

    @Test
    internal fun `register() - 기존에 존재하는 구간의 현재역에 연관된 구간을 추가할 경우`() {
        // given
        val line = Line.from("테스트노선1")
        val station1 = Station.from("테스트역1")
        val station2 = Station.from("테스트역2")
        val station3 = Station.from("테스트역3")
        val request = SectionRegisterRequest("테스트노선1", "테스트역2", "테스트역3", 2, 3)

        lineRepository.save(line)
        stationRepository.saveAll(station1, station2, station3)
        sectionRepository.save(Section.from("테스트노선1", "테스트역1", "테스트역3", 2, 3))

        // when
        sectionService.register(request)

        // then
        assertThat(sectionRepository).satisfies {
            assertThat(it.findAll()).hasSize(2)
            assertThat(it.existsByLineAndPreStationAndStation(line, station1, station2)).isTrue
            assertThat(it.existsByLineAndPreStationAndStation(line, station2, station3)).isTrue
        }
    }
}
