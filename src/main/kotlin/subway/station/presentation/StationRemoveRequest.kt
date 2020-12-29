package subway.station.presentation

import subway.station.domain.Station

data class StationRemoveRequest(val name: String) {
    fun toEntity() = Station.from(name)
}
