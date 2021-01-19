package subway.repository

import subway.domain.Station

object StationRepository {
    val stations = mutableListOf<Station>()

    fun stations() = stations.toList()

    fun addStation(station: Station) = stations.add(station)

    fun findStationByName(name: String): Station = stations().first { it.name == name }

    fun deleteStationByName(name: String) = stations.removeIf { it.name == name }
}
