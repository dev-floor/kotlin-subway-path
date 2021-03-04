package subway.service

import subway.repository.LineRepository
import subway.repository.StationRepository

class AllContentsService(
    private val lineRepository: LineRepository = LineRepository,
    private val stationRepository: StationRepository = StationRepository
) {
    fun getLines(): List<Any> = lineRepository.findAll().map { it.name }

    fun getStations(): List<Any> = stationRepository.findAll().map { it.name }
}
