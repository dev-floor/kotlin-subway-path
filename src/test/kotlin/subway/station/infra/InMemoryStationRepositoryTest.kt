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
    internal fun `save_InMemoryStationRepository에 데이터를 저장`() {
        // given
        val station = Station.from("테스트역3")

        // when
        stationRepository.save(station)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 1)
    }

    @Test
    internal fun `saveAll_InMemoryStationRepository에 여러 데이터를 저장`() {
        // given
        val station1 = Station.from("테스트역3")
        val station2 = Station.from("테스트역4")

        // when
        stationRepository.saveAll(station1, station2)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 2)
    }

    @Test
    internal fun `saveAll_InMemoryStationRepository에 리스트로 된 데이터를 저장`() {
        // given
        val stations = listOf(Station.from("테스트역3"), Station.from("테스트역4"))

        // when
        stationRepository.saveAll(stations)

        // then
        assertThat(stationRepository.findAll()).hasSize(STATION_FIXTURES.size + 2)
    }

    @Test
    internal fun `findAll_InMemoryStationRepository의 모든 데이터를 조회`() {
        // when
        val stations = stationRepository.findAll()

        // then
        assertThat(stations).hasSize(STATION_FIXTURES.size)
    }

    @Test
    internal fun `existsByName_InMemoryStationRepository에서 이름에 해당하는 역이 존재하는지 확인`() {
        // when
        val actual = stationRepository.existsByName("테스트역1")

        // then
        assertThat(actual).isTrue
    }

    @Test
    internal fun `delete_InMemoryStationRepository에서 해당하는 역을 삭제`() {
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
