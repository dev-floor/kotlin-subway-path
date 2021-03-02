package subway.domain

import subway.repository.LineRepository.existsByName

class Line(val name: String) {

    init {
        require(LINE_NAME_MAX_LENGTH <= name.length)
    }

    fun validLineToRegister() = !existsByName(this.name)

    companion object {
        const val LINE_NAME_MAX_LENGTH = 2
    }
}
