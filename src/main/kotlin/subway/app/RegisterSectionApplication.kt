package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.*

const val DEFAULT_TIME = 3
const val DEFAULT_DISTANCE = 2

fun registerSection() {
    val line = Line(getLineNameToRegister())

    val upwardStation = Station(getUpwardStationName())
    val downwardStation = Station(getDownwardStationName())
    val distance = getDistance()
    val time = getTime()

    val section = Section(upwardStation, downwardStation, distance, time)

    if (section.validSectionToRegister()) {
        SectionRepository.addSection(section)
        if (section.downExist()) {
            val upperStationName = SectionRepository.getUpwardStationByDownwardName(downwardStation.name)
            val upperStation = StationRepository.findStationByName(upperStationName)
            val additionalSection = Section(upperStation, upwardStation, DEFAULT_TIME, DEFAULT_DISTANCE)
            SectionRepository.addSection(additionalSection)
        }
        if (section.upExist()) {
            val downerStationName = SectionRepository.getDownwardStationByUpwardName(upwardStation.name)
            val downerStation = StationRepository.findStationByName(downerStationName)
            val additionalSection = Section(downwardStation, downerStation, DEFAULT_TIME, DEFAULT_DISTANCE)
            SectionRepository.addSection(additionalSection)
        }
    }

    infoMessage()
    succeedRegisterStation()
}
