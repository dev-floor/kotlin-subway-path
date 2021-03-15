package subway.repository

import subway.domain.Station

object StationRepository {
    private val stations = mutableListOf<Station>()

    private fun stations() = stations.toList()

    fun add(station: Station) = stations.add(station)

    fun existsByName(name: String): Boolean = stations().any { it.match(name) }

    fun deleteByName(name: String) = stations.removeIf { it.match(name) }

    fun findAll() = stations()
}
