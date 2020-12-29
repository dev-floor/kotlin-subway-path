package subway.line.domain

import subway.common.domain.Name

data class Line(val name: Name) {
    companion object {
        fun from(name: String) = Line(Name(name))
    }
}
