package subway.repository

import subway.domain.Station

object StationRepository {
    private val stations = mutableListOf<Station>()

    fun stations() = stations.toList()

    fun addStation(station: Station) {
        stations.add(station)
    }

    fun deleteStationByName(name: String) = stations.removeIf { it.name == name }
}
