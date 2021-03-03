package subway.app

import subway.domain.Section
import subway.repository.SectionRepository

class RegisterSection(val section: Section) {
    fun register() {
        // Additional section
        if (SectionRepository.existsByUpward(section.line, section.upwardStation))
            registerAdditionalSection(section)

        SectionRepository.add(section)

        // terminal change
        if (isDownwardTerminal(section.line.name, section.upwardStation.name))
            changeTerminalStation(section)
    }

    private fun registerAdditionalSection(section: Section) {
        val upwardSection = SectionRepository.findByUpward(section.upwardStation)
        SectionRepository.delete(section.line, section.upwardStation, upwardSection.downwardStation)
        SectionRepository.add(
            Section(
                line = section.line,
                upwardStation = section.downwardStation,
                downwardStation = upwardSection.downwardStation
            )
        )
    }

    private fun changeTerminalStation(section: Section) =
        SectionRepository.findAll().filter {
            it.line.name == section.line.name &&
                it.downwardStation.name == section.upwardStation.name
        }
            .map {
                it.downwardStation.isDownwardTerminal = false
                section.downwardStation.isDownwardTerminal = true
            }

    private fun isDownwardTerminal(lineName: String, stationName: String): Boolean =
        SectionRepository.findAll().any {
            it.line.name == lineName &&
                it.downwardStation.name == stationName &&
                it.downwardStation.isDownwardTerminal
        }
}
