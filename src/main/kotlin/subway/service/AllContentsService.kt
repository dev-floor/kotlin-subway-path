package subway.service

import subway.repository.LineRepository
import subway.repository.StationRepository

object AllContentsService {
    fun getLines(): List<String> = LineRepository.findAll().map { it.name }

    fun getStations(): List<String> = StationRepository.findAll().map { it.name }
}
