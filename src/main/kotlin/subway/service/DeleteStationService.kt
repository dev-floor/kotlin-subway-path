package subway.service

import subway.repository.SectionRepository
import subway.repository.StationRepository

object DeleteStationService {
    private fun validate(name: String) {
        require(
            SectionRepository.findAll()
                .none { it.matchUpward(name) || it.matchDownward(name) }
        )
    }

    fun delete(name: String) {
        validate(name)
        StationRepository.deleteByName(name)
    }
}
