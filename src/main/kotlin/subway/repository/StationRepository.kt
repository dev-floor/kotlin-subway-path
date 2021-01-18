package subway.repository

import subway.domain.Station

object StationRepository {
    val stations = mutableListOf<Station>()

    fun stations() = stations.toList()

    fun addStation(station: Station) {
        if(stations().none { it.name === station.name }) stations.add(station)
    }

    fun deleteStationByName(name: String) = stations.removeIf { it.name == name }
}
