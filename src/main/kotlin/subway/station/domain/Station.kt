package subway.station.domain

import subway.common.domain.Name

class Station(val name: Name) {
    fun match(name: String) = this.name.match(name)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Station

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    companion object {
        fun from(name: String) = Station(Name(name))
    }
}
