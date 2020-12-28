package subway.station.infra

import subway.station.domain.Station
import subway.station.domain.StationRepository

class InMemoryStationRepository : StationRepository {
    override fun stations() = STATIONS.toList()

    override fun addStation(station: Station) {
        STATIONS.add(station)
    }

    override fun deleteStation(name: String) = STATIONS.removeIf { it.name == name }

    companion object {
        private val STATIONS = mutableListOf<Station>()
    }
}
