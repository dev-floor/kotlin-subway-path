package subway.service

import subway.domain.Line
import subway.repository.LineRepository
import subway.view.errorMessage

object RegisterLineService {
    private fun validate(name: String) = require(!LineRepository.existsByName(name)) { errorMessage() }

    fun register(name: String): Line {
        validate(name)
        return Line(name).also {
            LineRepository.add(it)
        }
    }
}
