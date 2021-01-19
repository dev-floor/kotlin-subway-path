package subway.domain

import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.repository.StationRepository.existStationByName

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    var upwardTerminal = false

    var downwardTerminal = false

    fun validStationToRegister() = !existStationByName(this.name)

    fun changeTerminalStation() {
        if(SectionRepository.existUpperName(this.name)){
            val downwardName = SectionRepository.findDownwardNameByUpwardName(this.name)
            val downwardStation = StationRepository.findStationByName(downwardName)
            downwardStation.upwardTerminal = true
        }
        else if(SectionRepository.existDownerName(this.name)){
            val upwardName = SectionRepository.findDownwardNameByUpwardName(this.name)
            val upwardStation = StationRepository.findStationByName(upwardName)
            upwardStation.downwardTerminal = true
        }
    }

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
