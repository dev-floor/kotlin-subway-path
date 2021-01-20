package subway.domain

import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.repository.StationRepository.existStationByName

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    val upwardTerminal = false
    var downwardTerminal = false

    fun validStationToRegister() = !existStationByName(this.name)

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
