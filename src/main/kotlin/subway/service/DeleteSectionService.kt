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
    val repository: SectionRepository = SectionRepository
) {
    init {
        require(
            repository.findAll()
                .count { it.line.name == line.name } > MIN_STATION_COUNT_IN_SECTION
        )
        require(
            repository.findAll()
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
            repository.findByDownward(line, upwardStation)
                .downwardStation.isDownwardTerminal = true
        }

        repository.delete(line, upwardStation, downwardStation)
    }

    private fun biConnectedSection(upwardStation: Station, downwardStation: Station, line: Line) {
        val downwardSection = repository.findByUpward(line, downwardStation)
        val upwardSection = repository.findByDownward(line, downwardStation)
        repository.add(
            Section(
                line = LineRepository.findByName(line.name),
                upwardStation = upwardStation,
                downwardStation = repository.findByUpward(line, upwardStation).downwardStation,
                distance = downwardSection.distance!! + upwardSection.distance!!,
                time = downwardSection.time!! + upwardSection.time!!
            )
        )
    }

    companion object {
        const val MIN_STATION_COUNT_IN_SECTION = 1
    }
}
