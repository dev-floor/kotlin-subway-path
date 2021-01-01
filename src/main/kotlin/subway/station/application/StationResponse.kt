package subway.station.application

import subway.station.domain.Station

data class StationResponse(val name: String) {
    companion object {
        fun from(station: Station) = StationResponse(station.name.name)
    }
}
