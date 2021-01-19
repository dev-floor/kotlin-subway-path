package subway.domain

import subway.repository.SectionRepository

class Section(val upwardStation: Station, val downwardStation: Station, val time: Int, val distance: Int) {

    fun downExist() = SectionRepository.existDownwardByName(downwardStation.name)

    fun upExist() = SectionRepository.existUpwardByName(upwardStation.name)

    fun validSectionToRegister() = !(downExist() && upExist())

}
