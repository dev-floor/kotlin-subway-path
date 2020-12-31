package subway.station.domain

import subway.common.domain.Name

class Station private constructor(val name: Name) {
    fun match(name: String) = this.name.match(name)

    fun isUpwardEndStation() = this == UPWARD_END_STATION

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
        val UPWARD_END_STATION = Station(Name("UPWARD_END_STATION"))

        fun valueOf(name: String): Station {
            if (name.isBlank()) {
                return UPWARD_END_STATION
            }
            return Station(Name(name))
        }
    }
}
