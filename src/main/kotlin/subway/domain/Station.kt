package subway.domain

import subway.repository.StationRepository.existsByName

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    var upwardTerminal = false
    var downwardTerminal = false

    fun validStationToRegister() = !existsByName(this.name)

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
