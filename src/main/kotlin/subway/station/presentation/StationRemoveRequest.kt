package subway.station.presentation

import subway.common.domain.Name
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.station.domain.Station

data class StationRemoveRequest(val stationName: String) {
    init {
        require(stationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }

    fun toStation() = Station.from(stationName)
}
