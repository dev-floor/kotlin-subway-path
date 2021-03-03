package subway.app

import subway.domain.Section
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

class RegisterSection(
    val section: Section,
    val repository: SectionRepository = SectionRepository
) {
    init {
        require(StationRepository.existsByName(section.upwardStation.name))
        require(StationRepository.existsByName(section.downwardStation.name))
        require(LineRepository.existsByName(section.line.name))
        require(
            SectionRepository.existsByUpward(section.line, section.upwardStation) &&
                    SectionRepository.existsByDownward(section.line, section.downwardStation)
        )
    }

    fun register() {
        // Additional section
        if (repository.existsByUpward(section.line, section.upwardStation))
            registerAdditionalSection(section)

        checkFirstSection(section)
        repository.add(section)

        // terminal change
        if (repository.findAll()
            .any {
                it.line.name == section.line.name &&
                    it.downwardStation.name == section.upwardStation.name &&
                    it.downwardStation.isDownwardTerminal
            }
        )
            changeTerminalStation(section)
    }

    private fun registerAdditionalSection(section: Section) =
        repository.findByUpward(section.line, section.upwardStation).let {
            repository.delete(section.line, section.upwardStation, it.downwardStation)
            repository.add(
                Section(
                    line = section.line,
                    upwardStation = section.downwardStation,
                    downwardStation = it.downwardStation
                )
            )
        }

    private fun changeTerminalStation(section: Section) =
        repository.findAll()
            .filter {
                it.line.name == section.line.name &&
                    it.downwardStation.name == section.upwardStation.name
            }
            .map {
                it.downwardStation.isDownwardTerminal = false
                section.downwardStation.isDownwardTerminal = true
            }

    companion object {
        fun checkFirstSection(section: Section) {
            if (!SectionRepository.existsByDownward(section.line, section.downwardStation) &&
                !SectionRepository.existsByUpward(section.line, section.upwardStation) &&
                !SectionRepository.existsByDownward(section.line, section.upwardStation) &&
                !SectionRepository.existsByUpward(section.line, section.downwardStation)
            ) {
                section.upwardStation.isUpwardTerminal = true
                section.downwardStation.isDownwardTerminal = true
            }
        }
    }
}
