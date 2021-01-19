package subway.repository

import subway.domain.Station

object StationRepository {
    private val stations = mutableListOf<Station>()

    fun stations() = stations.toList()

    fun addStation(station: Station) = stations.add(station)

    fun findStationByName(name: String): Station = stations().first { it.name == name }

    fun existStationByName(name: String): Boolean = stations().any { it.name == name }

    fun validStationToDelete(name: String) = !SectionRepository.existStationInLine(name)

    fun deleteStationByName(name: String) = stations
            .removeIf { it.name == name && this.validStationToDelete(it.name) }

}
