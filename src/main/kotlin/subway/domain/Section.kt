package subway.domain

import subway.repository.SectionRepository

class Section(val line: Line, val upwardStation: Station, val downwardStation: Station, val time: Int, val distance: Int) {

    fun downExist() = SectionRepository.existDownwardByName(line.name, downwardStation.name)

    private fun upExist() = SectionRepository.existUpwardByName(line.name, upwardStation.name)

    fun validSectionToRegister() = !(downExist() && upExist())
}
