package subway.station.infra

import subway.station.domain.Station
import subway.station.domain.StationRepository

class InMemoryStationRepository : StationRepository {
    private val stations: MutableList<Station> = mutableListOf()

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

    override fun exists(station: Station) = stations.any { it == station }

    override fun delete(station: Station) = stations.removeIf { it == station }

    override fun deleteAll() = stations.clear()
}
