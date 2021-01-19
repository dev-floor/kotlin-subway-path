package subway.domain

import subway.repository.SectionRepository
import subway.repository.StationRepository

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    private fun validStationToRegister() = !this.stationExist()

    private fun stationExist(): Boolean = StationRepository.stations().none { it.name === this.name }

    private fun validStationToDelete(existInLine: Boolean) = !existInLine

    fun addStation() {
        if(this.validStationToRegister())
            StationRepository.addStation(this)
    }

    fun deleteStation() {
        if(this.validStationToDelete(SectionRepository.existStationInLine(this)))
            StationRepository.deleteStationByName(this.name)
    }

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
