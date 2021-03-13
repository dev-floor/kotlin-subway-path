package subway.service

import subway.domain.Section
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

class RegisterSectionService(
    private val section: Section,
) {
    init {
        require(StationRepository.existsByName(section.upwardStation.name))
        require(StationRepository.existsByName(section.downwardStation.name))
        require(LineRepository.existsByName(section.line.name))
        require(
            SectionRepository.existsByLineNameAndUpwardName(section.line, section.upwardStation) &&
                SectionRepository.existsByLineNameAndDownwardName(section.line, section.downwardStation)
        )
    }

    fun register() {
        // Additional section
        if (SectionRepository.existsByLineNameAndUpwardName(section.line, section.upwardStation))
            registerAdditionalSection(section)

        checkFirstSection(section)
        SectionRepository.add(section)

        // terminal change
        if (SectionRepository.findAll()
            .any {
                it.line.name == section.line.name &&
                    it.downwardStation.name == section.upwardStation.name &&
                    it.downwardStation.isDownwardTerminal
            }
        )
            changeTerminalStation(section)
    }

    private fun registerAdditionalSection(section: Section) =
        SectionRepository.findByLineNameAndUpwardName(section.line, section.upwardStation).let {
            SectionRepository.delete(section.line, section.upwardStation, it.downwardStation)
            SectionRepository.add(
                Section(
                    line = section.line,
                    upwardStation = section.downwardStation,
                    downwardStation = it.downwardStation
                )
            )
        }

    private fun changeTerminalStation(section: Section) =
        SectionRepository.findAll()
            .filter {
                it.line.name == section.line.name &&
                    it.downwardStation.name == section.upwardStation.name
            }
            .map {
                it.downwardStation.isDownwardTerminal = false
                section.downwardStation.isDownwardTerminal = true
            }

    private fun checkFirstSection(section: Section) {
        if (!SectionRepository.existsByLineNameAndDownwardName(section.line, section.downwardStation) &&
            !SectionRepository.existsByLineNameAndUpwardName(section.line, section.upwardStation) &&
            !SectionRepository.existsByLineNameAndDownwardName(section.line, section.upwardStation) &&
            !SectionRepository.existsByLineNameAndUpwardName(section.line, section.downwardStation)
        ) {
            section.upwardStation.isUpwardTerminal = true
            section.downwardStation.isDownwardTerminal = true
        }
    }
}
