package subway.line.application

import subway.common.domain.Name
import subway.common.exception.INVALID_NAME_MESSAGE
import subway.line.domain.Line

data class LineRemoveRequest(val lineName: String) {
    val line get() = Line.from(lineName)

    init {
        require(lineName.length >= Name.MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }
}
