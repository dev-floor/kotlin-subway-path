package subway.service

import subway.domain.Line
import subway.repository.LineRepository
import subway.view.errorMessage

object LineService {

    fun register(name: String): Line {
        require(!LineRepository.existsByName(name)) { errorMessage() }
        return Line(name).also {
            LineRepository.add(it)
        }
    }

    fun delete(name: String) = LineRepository.deleteByName(name)

    fun getLines(): List<String> = LineRepository.findAll().map { it.name }
}
