package subway.app

import subway.domain.Section
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.SectionRepository.distanceByUpwardName
import subway.repository.SectionRepository.existUpwardByName
import subway.repository.SectionRepository.timeByUpwardName
import subway.repository.StationRepository
import subway.view.getDownwardNameOfSectionToDelete
import subway.view.getLineNameOfSectionToDelete
import subway.view.getUpwardNameOfSectionToDelete
import subway.view.infoMessage
import subway.view.succeedDeleteSection

const val MIN_STATION_COUNT_IN_SECTION = 1

fun deleteSection() {
    val lineName = getLineNameOfSectionToDelete()
    val upwardStationName = getUpwardNameOfSectionToDelete()
    val downwardStationName = getDownwardNameOfSectionToDelete()

    require(SectionRepository.stationCountInSection(lineName) > MIN_STATION_COUNT_IN_SECTION)
    require(SectionRepository.continuousStation(upwardStationName, downwardStationName))

    biConnectedSection(upwardStationName, downwardStationName, lineName)

    checkTerminalStation(upwardStationName, downwardStationName, lineName)

    SectionRepository.deleteSection(lineName, upwardStationName, downwardStationName)

    infoMessage()
    succeedDeleteSection()
}

fun biConnectedSection(upwardStationName: String, downwardStationName: String, lineName: String) {
    if (existUpwardByName(lineName, downwardStationName)) {
        val newLine = LineRepository.findLineByName(lineName)
        val newUpwardStation = StationRepository.findStationByName(upwardStationName)
        val newDownwardStationName = SectionRepository.findDownwardNameByUpwardName(downwardStationName)
        val newDownwardStation = StationRepository.findStationByName(newDownwardStationName)
        val distanceSum = getDistanceSum(upwardStationName, downwardStationName, lineName)
        val timeSum = getTimeSum(upwardStationName, downwardStationName, lineName)
        val section = Section(newLine, newUpwardStation, newDownwardStation, timeSum, distanceSum)
        SectionRepository.addSection(section)
    }
}

fun checkTerminalStation(upwardStationName: String, downwardStationName: String, lineName: String) {
    val downwardStation = StationRepository.findStationByName(downwardStationName)
    val upwardSection = SectionRepository.findSectionByDownwardName(lineName, upwardStationName)

    if (downwardStation.downwardTerminal) {
        downwardStation.downwardTerminal = false
        upwardSection.downwardStation.downwardTerminal = true
    }
}

fun getDistanceSum(upwardStationName: String, downwardStationName: String, lineName: String) =
    distanceByUpwardName(lineName, upwardStationName) + distanceByUpwardName(lineName, downwardStationName)

fun getTimeSum(upwardStationName: String, downwardStationName: String, lineName: String): Int =
    timeByUpwardName(lineName, upwardStationName) + timeByUpwardName(lineName, downwardStationName)
