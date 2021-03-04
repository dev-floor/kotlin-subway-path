package subway.service

import subway.repository.SectionRepository
import subway.repository.StationRepository

class DeleteStationService(val name: String) {
    init {
        require(
            !SectionRepository.findAll()
                .any { it.downwardStation.name == name || it.upwardStation.name == name }
        )
    }

    fun delete() = StationRepository.deleteByName(name)
}
