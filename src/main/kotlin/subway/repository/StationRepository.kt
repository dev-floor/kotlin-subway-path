package subway.repository

import subway.domain.Station

object StationRepository {
    private val stations = mutableListOf<Station>()

    private fun stations() = stations.toList()

    fun add(station: Station) = stations.add(station)

    fun existsByName(name: String): Boolean = stations().any { it.name == name }

    fun deleteByName(name: String) = stations.removeIf { it.name == name }

    fun findAll() = stations()
}
