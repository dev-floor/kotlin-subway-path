package subway.app

import subway.service.PathService
import subway.view.getDeparture
import subway.view.getDestination
import subway.view.selectNumber
import subway.view.showPathResult
import subway.view.showSelectPath

fun path() {
    showSelectPath()
    select = selectNumber()
    if (select == BACK) return

    showPathResult(
        PathService(
            departure = getDeparture(),
            destination = getDestination(),
        ).path(select.toInt())
    )
}
