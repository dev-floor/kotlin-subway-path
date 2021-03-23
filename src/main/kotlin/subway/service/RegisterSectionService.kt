package subway.service

import subway.domain.Section
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

object RegisterSectionService {

    fun register(section: Section) {
        validate(section)

        // Additional section
        if (SectionRepository.existsByLineNameAndUpwardName(section.line.name, section.upwardStation.name))
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

    private fun validate(section: Section) {
        require(StationRepository.existsByName(section.upwardStation.name))
        require(StationRepository.existsByName(section.downwardStation.name))
        require(LineRepository.existsByName(section.line.name))
        require(
            SectionRepository.existsByLineNameAndUpwardName(section.line.name, section.upwardStation.name) &&
                SectionRepository.existsByLineNameAndDownwardName(section.line.name, section.downwardStation.name)
        )
    }

    private fun registerAdditionalSection(section: Section) =
        SectionRepository.findByLineNameAndUpwardName(section.line.name, section.upwardStation.name).apply {
            SectionRepository.delete(section.line.name, section.upwardStation.name, downwardStation.name)
            SectionRepository.add(
                Section(
                    line = section.line,
                    upwardStation = section.downwardStation,
                    downwardStation = downwardStation
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
        if (!SectionRepository.existsByLineNameAndDownwardName(section.line.name, section.downwardStation.name) &&
            !SectionRepository.existsByLineNameAndUpwardName(section.line.name, section.upwardStation.name) &&
            !SectionRepository.existsByLineNameAndDownwardName(section.line.name, section.upwardStation.name) &&
            !SectionRepository.existsByLineNameAndUpwardName(section.line.name, section.downwardStation.name)
        ) {
            section.upwardStation.isUpwardTerminal = true
            section.downwardStation.isDownwardTerminal = true
        }
    }
}
