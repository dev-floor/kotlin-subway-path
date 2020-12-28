package subway.domain

object StationRepository {
    private val stations = mutableListOf<Station>()

    fun stations() = stations.toList()

    fun addStation(station: Station) {
        stations.add(station)
    }

    fun deleteStation(name: String) = stations.removeIf { it.name == name }
}
