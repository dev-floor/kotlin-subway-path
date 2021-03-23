package subway.service

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.dto.SectionRequest
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

object SectionService {

    private const val MIN_STATION_COUNT_IN_SECTION = 1

    fun register(sectionRequest: SectionRequest) {
        val section = Section.toEntity(
            line = sectionRequest.line,
            upwardStation = sectionRequest.upwardStation,
            downwardStation = sectionRequest.downwardStation,
            time = sectionRequest.time,
            distance = sectionRequest.distance
        )

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

    fun delete(sectionRequest: SectionRequest) {
        val line = Line.toEntity(sectionRequest.line.name)
        val upwardStation = Station.toEntity(sectionRequest.upwardStation.name)
        val downwardStation = Station.toEntity(sectionRequest.downwardStation.name)

        validateToDelete(line.name, upwardStation.name, downwardStation.name)

        if (SectionRepository.existsByLineNameAndUpwardName(line.name, downwardStation.name))
            applyToChangedSection(upwardStation, downwardStation, line)

        if (downwardStation.isDownwardTerminal) {
            downwardStation.isDownwardTerminal = false
            SectionRepository.findByLineNameAndDownwardName(line.name, upwardStation.name)
                .downwardStation.isDownwardTerminal = true
        }

        SectionRepository.delete(line.name, upwardStation.name, downwardStation.name)
    }

    private fun validateToDelete(
        lineName: String,
        upwardStationName: String,
        downwardStationName: String
    ) {
        require(
            SectionRepository.findAll()
                .count { it.line.name == lineName } > MIN_STATION_COUNT_IN_SECTION
        )
        require(
            SectionRepository.findAll()
                .any {
                    it.upwardStation.name == upwardStationName &&
                        it.downwardStation.name == downwardStationName
                }
        )
    }

    private fun applyToChangedSection(upwardStation: Station, downwardStation: Station, line: Line) =
        SectionRepository.add(
            Section(
                line = LineRepository.findByName(line.name),
                upwardStation = upwardStation,
                downwardStation = SectionRepository.findByLineNameAndUpwardName(line.name, upwardStation.name).downwardStation,
            ).apply {
                val downwardSection = SectionRepository.findByLineNameAndUpwardName(line.name, downwardStation.name)
                val upwardSection = SectionRepository.findByLineNameAndDownwardName(line.name, downwardStation.name)
                distance = downwardSection.distance!! + upwardSection.distance!!
                time = downwardSection.time!! + upwardSection.time!!
            }
        )

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
