package subway.station.domain

interface StationRepository {
    fun save(station: Station)

    fun saveAll(vararg stations: Station)

    fun saveAll(stations: List<Station>)

    fun findAll(): List<Station>

    fun exists(station: Station): Boolean

    fun deleteByName(name: String): Boolean
}
