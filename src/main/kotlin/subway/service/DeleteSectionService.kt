package subway.service

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.SectionRepository.existsByLineNameAndUpwardName

object DeleteSectionService {

    private const val MIN_STATION_COUNT_IN_SECTION = 1

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

    fun delete(
        line: Line,
        upwardStation: Station,
        downwardStation: Station,
    ) {
        validateToDelete(line.name, upwardStation.name, downwardStation.name)

        if (existsByLineNameAndUpwardName(line.name, downwardStation.name))
            applyToChangedSection(upwardStation, downwardStation, line)

        if (downwardStation.isDownwardTerminal) {
            downwardStation.isDownwardTerminal = false
            SectionRepository.findByLineNameAndDownwardName(line.name, upwardStation.name)
                .downwardStation.isDownwardTerminal = true
        }

        SectionRepository.delete(line.name, upwardStation.name, downwardStation.name)
    }
}
