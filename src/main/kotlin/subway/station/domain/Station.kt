package subway.station.domain

import subway.common.domain.Name

data class Station(val name: Name) {
    fun match(name: String) = this.name.match(name)

    companion object {
        fun from(name: String) = Station(Name(name))
    }
}
