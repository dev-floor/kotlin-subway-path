package subway.station.domain

interface StationRepository {
    fun findAll(): List<Station>

    fun save(station: Station)

    fun saveAll(vararg stations: Station)

    fun deleteByName(name: String): Boolean
}
