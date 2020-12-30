package subway.section.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import subway.line.domain.Line
import subway.section.domain.Section
import subway.section.domain.SectionRepository
import subway.station.domain.Station

@Suppress("NonAsciiCharacters")
internal class InMemorySectionRepositoryTest {
    private lateinit var sectionRepository: SectionRepository

    @BeforeEach
    internal fun setUp() {
        sectionRepository = InMemorySectionRepository().apply { saveAll(SECTION_FIXTURES) }
    }

    @Test
    internal fun `save() - 구간을 저장`() {
        // given
        val section =
            Section(Line.from("테스트노선2"), Station.from("테스트역1"), Station.from("테스트역3"), 2, 3)

        // when
        sectionRepository.save(section)

        // then
        assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll() - vararg 타입의 여러 구간들을 저장`() {
        // given
        val section1 =
            Section(Line.from("테스트노선2"), Station.from("테스트역1"), Station.from("테스트역3"), 2, 3)
        val section2 =
            Section(Line.from("테스트노선2"), Station.from("테스트역3"), Station.from("테스트역4"), 2, 3)

        // when
        sectionRepository.saveAll(section1, section2)

        // then
        assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll() - list 타입의 여러 구간들을 저장`() {
        // given
        val sections = listOf(
            Section(Line.from("테스트노선2"), Station.from("테스트역1"), Station.from("테스트역3"), 2, 3),
            Section(Line.from("테스트노선2"), Station.from("테스트역3"), Station.from("테스트역4"), 2, 3)
        )

        // when
        sectionRepository.saveAll(sections)

        // then
        assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size + 2)
    }

    @Test
    internal fun `findByLineAndPreStation() - 해당하는 노선,이전역이 포함된 구간을 조회`() {
        // given
        val line = Line.from("테스트노선1")
        val station = Station.from("테스트역1")

        // when
        val actual = sectionRepository.findByLineAndPreStation(line, station)

        // then
        assertThat(actual).isNotNull
    }

    @Test
    internal fun `findByLineAndStation() - 해당하는 노선,현재역이 포함된 구간을 조회`() {
        // given
        val line = Line.from("테스트노선1")
        val station = Station.from("테스트역3")

        // when
        val actual = sectionRepository.findByLineAndStation(line, station)

        // then
        assertThat(actual).isNotNull
    }

    @Test
    internal fun `findAll() - 모든 구간들을 조회`() {
        // when
        val sections = sectionRepository.findAll()

        // then
        assertThat(sections).hasSize(SECTION_FIXTURES.size)
    }

    @Test
    internal fun `existsByPreStation() - 해당하는 이전역이 포함된 구간이 존재하는지 여부`() {
        // given
        val station = Station.from("테스트역1")

        // when
        val actual = sectionRepository.existsByPreStation(station)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `existsByStation() - 해당하는 현재역이 포함된 구간이 존재하는지 여부`() {
        // given
        val station = Station.from("테스트역3")

        // when
        val actual = sectionRepository.existsByStation(station)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `existsByLineAndPreStationAndStation() - 해당하는 노선,이전역,현재역과 일치하는 구간이 존재하는지 여부`() {
        // given
        val line = Line.from("테스트노선1")
        val upStation = Station.from("테스트역1")
        val downStation = Station.from("테스트역2")

        // when
        val actual =
            sectionRepository.existsByLineAndPreStationAndStation(line, upStation, downStation)

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `delete() - 해당하는 구간을 삭제`() {
        // given
        val section =
            Section(Line.from("테스트노선1"), Station.from("테스트역1"), Station.from("테스트역2"), 2, 3)

        // when
        val actual = sectionRepository.delete(section)

        // then
        assertAll(
            { assertThat(actual).isTrue },
            { assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size - 1) }
        )
    }

    companion object {
        private val SECTION_FIXTURES = listOf(
            Section(Line.from("테스트노선1"), Station.from("테스트역1"), Station.from("테스트역2"), 2, 3),
            Section(Line.from("테스트노선1"), Station.from("테스트역2"), Station.from("테스트역3"), 2, 3)
        )
    }
}
