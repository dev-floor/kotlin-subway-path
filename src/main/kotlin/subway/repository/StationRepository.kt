package subway.repository

import subway.domain.Station

object StationRepository {
    private val stations = mutableListOf<Station>()

    fun stations() = stations.toList()

    fun add(station: Station) = stations.add(station)

    fun findByName(name: String): Station = stations().first { it.name == name }

    fun existsByName(name: String): Boolean = stations().any { it.name == name }

    fun deleteByName(name: String) = stations.removeIf { it.name == name }
}
