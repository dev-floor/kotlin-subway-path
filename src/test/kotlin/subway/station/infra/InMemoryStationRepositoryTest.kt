package subway.station.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import subway.station.domain.Station
import subway.station.domain.StationRepository

@Suppress("NonAsciiCharacters")
internal class InMemoryStationRepositoryTest {
    private lateinit var stationRepository: StationRepository

    @BeforeEach
    internal fun setUp() {
        stationRepository = InMemoryStationRepository().apply { saveAll(STATION_FIXTURES) }
    }

    @Test
    internal fun `save() - 해당하는 역을 저장`() {
        // given
        val station = Station.from("테스트역3")

        // when
        stationRepository.save(station)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll() - vararg 타입의 여러 역들을 저장`() {
        // given
        val station1 = Station.from("테스트역3")
        val station2 = Station.from("테스트역4")

        // when
        stationRepository.saveAll(station1, station2)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll() - list 타입의 여러 역들을 저장`() {
        // given
        val stations = listOf(Station.from("테스트역3"), Station.from("테스트역4"))

        // when
        stationRepository.saveAll(stations)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 2)
    }

    @Test
    internal fun `findAll() - 모든 역들을 조회`() {
        // when
        val stations = stationRepository.findAll()

        // then
        assertThat(stations).hasSize(STATION_FIXTURES.size)
    }

    @Test
    internal fun `existsByName() - 해당하는 이름의 역이 존재하는지 확인`() {
        // when
        val actual = stationRepository.existsByName("테스트역1")

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `delete() - 해당하는 역을 삭제`() {
        // given
        val station = Station.from("테스트역1")

        // when
        stationRepository.delete(station)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size - 1)
    }

    companion object {
        private val STATION_FIXTURES = listOf(
            Station.from("테스트역1"),
            Station.from("테스트역2"),
        )
    }
}
