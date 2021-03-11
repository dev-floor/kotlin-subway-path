package subway.service

import subway.repository.LineRepository

class DeleteLineService {
    fun delete(name: String) = LineRepository.deleteByName(name)
}
