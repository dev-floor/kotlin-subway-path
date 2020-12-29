package subway.station.domain

import subway.common.domain.Name

data class Station(val name: Name) {
    companion object {
        fun from(name: String) = Station(Name(name))
    }
}
