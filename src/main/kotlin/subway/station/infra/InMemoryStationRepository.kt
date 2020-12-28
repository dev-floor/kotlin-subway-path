package subway.station.infra

import subway.station.domain.Station
import subway.station.domain.StationRepository

class InMemoryStationRepository : StationRepository {
    override fun findAll() = STATIONS.toList()

    override fun save(station: Station) {
        STATIONS.add(station)
    }

    override fun saveAll(vararg stations: Station) {
        STATIONS.addAll(stations)
    }

    override fun deleteByName(name: String) = STATIONS.removeIf { it.name == name }

    companion object {
        private val STATIONS = mutableListOf<Station>()
    }
}
