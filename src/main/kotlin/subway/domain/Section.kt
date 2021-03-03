package subway.domain

import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

class Section(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
    var time: Int = 3,
    var distance: Int = 2
) {
    init {
        require(StationRepository.existsByName(upwardStation.name))
        require(StationRepository.existsByName(downwardStation.name))
        require(LineRepository.existsByName(line.name))
        require(SectionRepository.existsByUpward(line, upwardStation)
                    && SectionRepository.existsByDownward(line, downwardStation))
    }
}
