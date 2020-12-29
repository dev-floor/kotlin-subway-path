package subway.section.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
    internal fun `findAll_InMemorySectionRepository의 모든 데이터를 조회`() {
        // when
        val sections = sectionRepository.findAll()

        // then
        assertThat(sections).hasSize(SECTION_FIXTURES.size)
    }

    @Test
    internal fun `save_InMemorySectionRepository에 데이터를 저장`() {
        // given
        val section = Section(Line("테스트노선2"), Station("테스트역1"), Station("테스트역3"), 2, 3)

        // when
        sectionRepository.save(section)

        // then
        assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll_InMemorySectionRepository에 여러 데이터를 저장`() {
        // given
        val section1 = Section(Line("테스트노선2"), Station("테스트역1"), Station("테스트역3"), 2, 3)
        val section2 = Section(Line("테스트노선2"), Station("테스트역3"), Station("테스트역4"), 2, 3)

        // when
        sectionRepository.saveAll(section1, section2)

        // then
        assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll_InMemorySectionRepository에 리스트로 된 데이터를 저장`() {
        // given
        val sections = listOf(
            Section(Line("테스트노선2"), Station("테스트역1"), Station("테스트역3"), 2, 3),
            Section(Line("테스트노선2"), Station("테스트역3"), Station("테스트역4"), 2, 3)
        )

        // when
        sectionRepository.saveAll(sections)

        // then
        assertThat(sectionRepository.findAll()).hasSize(SECTION_FIXTURES.size + 2)
    }

    companion object {
        private val SECTION_FIXTURES = listOf(
            Section(Line("테스트노선1"), Station("테스트역1"), Station("테스트역2"), 2, 3),
            Section(Line("테스트노선1"), Station("테스트역2"), Station("테스트역3"), 2, 3)
        )
    }
}
