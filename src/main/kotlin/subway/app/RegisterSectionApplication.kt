package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.*

const val DEFAULT_TIME = 3
const val DEFAULT_DISTANCE = 2

fun registerSection() {
    val lineName = getLineNameOfSectionToRegister()
    val line = Line(lineName)

    val upwardStationName = getUpwardNameOfSectionToRegister()
    val downwardStationName = getDownwardNameOfSectionToRegister()
    val distance = getSectionDistance()
    val time = getSectionTime()
    val section = Section(line, Station(upwardStationName), Station(downwardStationName), distance, time)

    require(StationRepository.existStationByName(upwardStationName))
    require(StationRepository.existStationByName(downwardStationName))
    require(LineRepository.existLineByName(lineName))
    require(section.validSectionToRegister())

    SectionRepository.addSection(section)

    checkAdditionalSection(section)

    infoMessage()
    succeedRegisterStation()
}

fun checkAdditionalSection(section: Section) {
    // 상행역에 존재
    if (section.downExist()) registerAdditionalDownSection(section)
    // 하행역에 존재
    if (section.upExist()) registerAdditionalUpSection(section)
}

fun registerAdditionalDownSection(section: Section) {
    val downerStationName = SectionRepository.findDownwardNameByUpwardName(section.upwardStation.name)
    val downerStation = StationRepository.findStationByName(downerStationName)
    val additionalSection = Section(section.line, section.downwardStation, downerStation, DEFAULT_TIME, DEFAULT_DISTANCE)
    SectionRepository.addSection(additionalSection)
}

fun registerAdditionalUpSection(section: Section) {
    val upperStationName = SectionRepository.findUpwardNameByDownwardName(section.downwardStation.name)
    val upperStation = StationRepository.findStationByName(upperStationName)
    val additionalSection = Section(section.line, upperStation, section.upwardStation, DEFAULT_TIME, DEFAULT_DISTANCE)
    SectionRepository.addSection(additionalSection)
}





