package subway.service

import subway.repository.LineRepository
import subway.repository.StationRepository

class AllContentsService {
    fun getLines(): List<Any> = LineRepository.findAll().map { it.name }

    fun getStations(): List<Any> = StationRepository.findAll().map { it.name }
}
