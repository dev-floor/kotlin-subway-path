package subway.line.domain

import subway.common.domain.Name

class Line(val name: Name) {
    companion object {
        fun from(name: String) = Line(Name(name))
    }
}
