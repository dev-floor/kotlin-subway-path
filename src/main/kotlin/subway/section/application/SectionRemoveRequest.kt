package subway.section.application

import subway.common.domain.Name
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.line.domain.Line
import subway.section.domain.Section
import subway.station.domain.Station

data class SectionRemoveRequest(
    val lineName: String,
    val upStationName: String,
    val downStationName: String,
) {
    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(upStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
        require(downStationName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }

    fun toSection() = Section(
        line = Line.from(lineName),
        preStation = Station.from(upStationName),
        station = Station.from(downStationName),
    )
}
