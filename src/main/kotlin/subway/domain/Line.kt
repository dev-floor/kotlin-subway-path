package subway.domain

import subway.repository.LineRepository.existLineByName

class Line(val name: String) {

    init {
        require(LINE_NAME_MAX_LENGTH <= name.length)
    }

    fun validLineToRegister() = !existLineByName(this.name)

    companion object {
        const val LINE_NAME_MAX_LENGTH = 2
    }
}
