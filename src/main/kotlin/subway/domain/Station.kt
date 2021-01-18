package subway.domain

import subway.repository.StationRepository

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
        require(StationRepository.stations().none { it.name === this.name })
    }

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
