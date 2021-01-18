package subway.domain

import subway.repository.LineRepository

class Line(val name: String) {

    init {
        require(LINE_NAME_MAX_LENGTH <= name.length)
        require(LineRepository.lines().none { it.name === this.name })
    }

    companion object {
        const val LINE_NAME_MAX_LENGTH = 2
    }
}
