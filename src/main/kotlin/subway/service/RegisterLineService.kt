package subway.service

import subway.domain.Line
import subway.repository.LineRepository
import subway.view.errorMessage

class RegisterLineService(val line: Line) {
    init {
        require(!LineRepository.existsByName(line.name)) { errorMessage() }
    }

    fun register() = LineRepository.add(line)
}
