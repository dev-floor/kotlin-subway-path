package subway.app

import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.getDownwardNameOfSectionToDelete
import subway.view.getLineNameOfSectionToDelete
import subway.view.getUpwardNameOfSectionToDelete

const val MIN_STATION_COUNT_IN_SECTION = 2

fun deleteSection() {
    val lineName = getLineNameOfSectionToDelete()
    val upwardStationName = getUpwardNameOfSectionToDelete()
    val downwardStationName = getDownwardNameOfSectionToDelete()

    require(SectionRepository.stationCountInSection(lineName) > MIN_STATION_COUNT_IN_SECTION)
    require(SectionRepository.continuousStation(upwardStationName, downwardStationName))

    SectionRepository.deleteSection(lineName, upwardStationName, downwardStationName)

    checkTerminalStation(upwardStationName, downwardStationName)
    applyTime(upwardStationName, downwardStationName)
}

fun checkTerminalStation(upwardStationName: String, downwardStationName: String) {
    val upwardStation = StationRepository.findStationByName(upwardStationName)
    val downwardStation = StationRepository.findStationByName(downwardStationName)
//
//    if(upwardStation.upwardTerminal){
//        upwardStation.changeTerminalStation()
//    }

    if(downwardStation.downwardTerminal) {
        downwardStation.downwardTerminal = false
        upwardStation.downwardTerminal = true
    }
}

fun applyTime(upwardStationName: String, downwardStationName: String) {

}