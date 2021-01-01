package subway.path.application

import subway.common.domain.Name
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.station.domain.Station

data class PathRequest(
    val pathType: String,
    val departureName: String,
    val destinationName: String,
) {
    val departure get() = Station.valueOf(departureName)

    val destination get() = Station.valueOf(destinationName)

    init {
        require(departureName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(destinationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }
}
