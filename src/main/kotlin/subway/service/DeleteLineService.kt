package subway.service

import subway.repository.LineRepository

object DeleteLineService {
    fun delete(name: String) = LineRepository.deleteByName(name)
}
