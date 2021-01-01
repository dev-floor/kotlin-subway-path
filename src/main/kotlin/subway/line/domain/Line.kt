package subway.line.domain

import subway.common.domain.Name

class Line private constructor(val name: Name) {
    fun match(name: String) = this.name.match(name)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    companion object : Comparator<Line> {
        fun from(name: String) = Line(Name(name))

        override fun compare(o1: Line, o2: Line) = o1.name.compareTo(o2.name)
    }
}
