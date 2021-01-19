package subway.domain

import subway.repository.LineRepository
import subway.repository.StationRepository

class Line(val name: String) {

    init {
        require(LINE_NAME_MAX_LENGTH <= name.length)
    }

    fun addLine() {
        if(this.validLineToRegister())
            LineRepository.addLine(this)
    }

    fun validLineToRegister() = !lineExist()

    private fun lineExist(): Boolean = LineRepository.lines().none { it.name === this.name }

    companion object {
        const val LINE_NAME_MAX_LENGTH = 2
    }
}
