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
    internal fun `findAll_InMemoryStationRepository의 모든 데이터를 조회`() {
        // when
        val stations = stationRepository.findAll()

        // then
        assertThat(stations).hasSize(STATION_FIXTURES.size)
    }

    @Test
    internal fun `save_InMemoryStationRepository에 데이터를 저장`() {
        // given
        val station = Station("테스트역3")

        // when
        stationRepository.save(station)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll_InMemoryStationRepository에 여러 데이터를 저장`() {
        // given
        val station1 = Station("테스트역3")
        val station2 = Station("테스트역4")

        // when
        stationRepository.saveAll(station1, station2)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll_InMemoryStationRepository에 리스트로 된 데이터를 저장`() {
        // given
        val stations = listOf(Station("테스트역3"), Station("테스트역4"))

        // when
        stationRepository.saveAll(stations)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 2)
    }

    @Test
    internal fun `InMemoryStationRepository의 데이터를 노선의 이름으로 삭제`() {
        // given
        val name = "테스트역1"

        // when
        stationRepository.deleteByName(name)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size - 1)
    }

    companion object {
        private val STATION_FIXTURES = listOf(
            Station("테스트역1"),
            Station("테스트역2"),
        )
    }
}
