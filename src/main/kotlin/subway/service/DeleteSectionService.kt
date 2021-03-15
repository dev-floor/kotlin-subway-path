package subway.service

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.SectionRepository.existsByLineNameAndUpwardName

class DeleteSectionService(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
) {
    init {
        require(
            SectionRepository.findAll()
                .count { it.line.name == line.name } > MIN_STATION_COUNT_IN_SECTION
        )
        require(
            SectionRepository.findAll()
                .any {
                    it.upwardStation.name == upwardStation.name &&
                        it.downwardStation.name == downwardStation.name
                }
        )
    }

    fun delete() {
        if (existsByLineNameAndUpwardName(line.name, downwardStation.name))
            biConnectedSection(upwardStation, downwardStation, line)

        if (downwardStation.isDownwardTerminal) {
            downwardStation.isDownwardTerminal = false
            SectionRepository.findByLineNameAndDownwardName(line.name, upwardStation.name)
                .downwardStation.isDownwardTerminal = true
        }

        SectionRepository.delete(line.name, upwardStation.name, downwardStation.name)
    }

    private fun biConnectedSection(upwardStation: Station, downwardStation: Station, line: Line) =
        SectionRepository.add(
            Section(
                line = LineRepository.findByName(line.name),
                upwardStation = upwardStation,
                downwardStation = SectionRepository.findByLineNameAndUpwardName(line.name, upwardStation.name).downwardStation,
            ).let {
                val downwardSection = SectionRepository.findByLineNameAndUpwardName(line.name, downwardStation.name)
                val upwardSection = SectionRepository.findByLineNameAndDownwardName(line.name, downwardStation.name)
                it.distance = downwardSection.distance!! + upwardSection.distance!!
                it.time = downwardSection.time!! + upwardSection.time!!

                it
            }
        )

    companion object {
        const val MIN_STATION_COUNT_IN_SECTION = 1
    }
}
