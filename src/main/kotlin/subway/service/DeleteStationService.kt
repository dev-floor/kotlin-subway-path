package subway.service

import subway.repository.SectionRepository
import subway.repository.StationRepository

class DeleteStationService(val name: String) {
    init {
        require(
            SectionRepository.findAll()
                .none { it.matchUpward(name) || it.matchDownward(name) }
        )
    }

    fun delete() = StationRepository.deleteByName(name)
}
