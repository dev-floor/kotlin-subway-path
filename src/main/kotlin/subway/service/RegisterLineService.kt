package subway.service

import subway.domain.Line
import subway.repository.LineRepository
import subway.view.errorMessage

object RegisterLineService {
    private fun validate(lineName: String) = require(!LineRepository.existsByName(lineName)) { errorMessage() }

    fun register(lineName: String): Line {
        validate(lineName)
        return Line(lineName).also {
            LineRepository.add(it)
        }
    }
}
