package subway.station.domain

interface StationRepository {
    fun stations(): List<Station>

    fun addStation(station: Station)

    fun deleteStation(name: String): Boolean
}
