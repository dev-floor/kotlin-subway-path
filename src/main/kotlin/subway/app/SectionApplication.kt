package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.init.ONE
import subway.init.THREE
import subway.init.TWO
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.*

fun adminSection() {
    showAdminSection()
    select = selectMessage()
    if (select == BACK) return

    when (select.toInt()) {
        ONE -> registerSection()
        TWO -> deleteSection()
    }
}

fun deleteSection() {
    TODO()
}

fun registerSection() {
    val line = Line(getRegisterLineName())

    val upwardStation = Station(getUpwardStationName())
    val downwardStation = Station(getDownwardStationName())
    val distance = getDistance()
    val time = getTime()

    val section = Section(upwardStation, downwardStation, distance, time)

    if (section.validSectionToRegister())
        SectionRepository.addSection(section)

    if (section.downExist()) {
        val upperStationName = SectionRepository.getUpwardStationByDownwardName(downwardStation.name)
        val upperStation = StationRepository.findStationByName(upperStationName)
        val additionalSection = Section(upperStation, upwardStation, THREE, TWO)
        SectionRepository.addSection(additionalSection)
    } else if (section.upExist()) {
        val downerStationName = SectionRepository.getUpwardStationByDownwardName(downwardStation.name)
        val downerStation = StationRepository.findStationByName(downerStationName)
        val additionalSection = Section(downwardStation, downerStation, THREE, TWO)
        SectionRepository.addSection(additionalSection)
    }

    infoMessage()
    succeedRegisterStation()
}
