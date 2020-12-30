package subway.line.domain

import subway.common.domain.Name

data class Line(val name: Name) {
    fun match(name: String) = this.name.match(name)

    companion object {
        fun from(name: String) = Line(Name(name))
    }
}
