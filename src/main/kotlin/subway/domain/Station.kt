package subway.domain

import subway.repository.StationRepository.existStationByName

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    fun validStationToRegister() = !existStationByName(this.name)

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
