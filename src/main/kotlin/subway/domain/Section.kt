package subway.domain

import subway.repository.SectionRepository

class Section(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
    var time: Int = 3,
    var distance: Int = 2
) {

    private fun downExist() = SectionRepository.existDownwardByName(line.name, downwardStation.name)

    fun upExist() = SectionRepository.existUpwardByName(line.name, upwardStation.name)

    fun validSectionToRegister() = !(downExist() && upExist())
}
