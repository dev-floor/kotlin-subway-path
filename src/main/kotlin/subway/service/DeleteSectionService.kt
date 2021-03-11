package subway.service

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.SectionRepository.existsByUpward

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
        if (existsByUpward(line, downwardStation))
            biConnectedSection(upwardStation, downwardStation, line)

        if (downwardStation.isDownwardTerminal) {
            downwardStation.isDownwardTerminal = false
            SectionRepository.findByDownward(line, upwardStation)
                .downwardStation.isDownwardTerminal = true
        }

        SectionRepository.delete(line, upwardStation, downwardStation)
    }

    private fun biConnectedSection(upwardStation: Station, downwardStation: Station, line: Line) =
        SectionRepository.add(
            Section(
                line = LineRepository.findByName(line.name),
                upwardStation = upwardStation,
                downwardStation = SectionRepository.findByUpward(line, upwardStation).downwardStation,
            ).let {
                val downwardSection = SectionRepository.findByUpward(line, downwardStation)
                val upwardSection = SectionRepository.findByDownward(line, downwardStation)
                it.distance = downwardSection.distance!! + upwardSection.distance!!
                it.time = downwardSection.time!! + upwardSection.time!!

                it
            }
        )

    companion object {
        const val MIN_STATION_COUNT_IN_SECTION = 1
    }
}
