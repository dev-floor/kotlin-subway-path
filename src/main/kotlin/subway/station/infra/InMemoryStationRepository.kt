package subway.station.infra

import subway.station.domain.Station
import subway.station.domain.StationRepository

class InMemoryStationRepository : StationRepository {
    private val stations = mutableListOf<Station>()

    override fun save(station: Station) {
        stations.add(station)
    }

    override fun saveAll(vararg stations: Station) {
        this.stations.addAll(stations)
    }

    override fun saveAll(stations: List<Station>) {
        this.stations.addAll(stations)
    }

    override fun findAll() = stations.toList()

    override fun existsByName(name: String) = stations.any { it.match(name) }

    override fun deleteByName(name: String) = stations.removeIf { it.name.match(name) }
}
