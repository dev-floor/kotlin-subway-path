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
import subway.section.domain.SectionRepository
import subway.section.infra.InMemorySectionRepository
import subway.section.presentation.SectionRegisterRequest
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
    internal fun `register_구간을 등록`() {
        // given
        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)
        lineRepository.save(Line.from("테스트노선1"))
        stationRepository.save(Station.from("테스트역1"))
        stationRepository.save(Station.from("테스트역2"))

        // when
        sectionService.register(request)

        // then
        assertThat(sectionRepository.findAll()).hasSize(1)
    }

    @Test
    internal fun `register_존재하지 않는 노선일 경우 예외 발생`() {
        // given
        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)
        stationRepository.save(Station.from("테스트역1"))
        stationRepository.save(Station.from("테스트역2"))

        // then
        assertThatIllegalArgumentException().isThrownBy { sectionService.register(request) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["테스트역1", "테스트역2"])
    internal fun `register_존재하지 않는 역일 경우 예외 발생`(stationName: String) {
        // given
        val request = SectionRegisterRequest("테스트노선1", "테스트역1", "테스트역2", 2, 3)
        lineRepository.save(Line.from("테스트노선1"))
        stationRepository.save(Station.from(stationName))

        // then
        assertThatIllegalArgumentException().isThrownBy { sectionService.register(request) }
    }
}
