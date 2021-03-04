package subway.service

import subway.repository.LineRepository

class DeleteLineService(val name: String) {
    fun delete() = LineRepository.deleteByName(name)
}
