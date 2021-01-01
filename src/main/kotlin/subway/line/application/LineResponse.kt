package subway.line.application

import subway.line.domain.Line

data class LineResponse(val name: String) {
    fun toLine() = Line.from(name)

    companion object {
        fun from(line: Line) = LineResponse(line.name.name)
    }
}
