package subway.app

import subway.dto.PathRequest
import subway.service.PathService
import subway.view.inputDeparture
import subway.view.inputDestination
import subway.view.inputSelect
import subway.view.pathPage
import subway.view.pathResult

fun path() {
    pathPage()
    val select = inputSelect()
    if (select == BACK) return

    pathResult(
        PathService.path(
            PathRequest(
                departure = inputDeparture(),
                destination = inputDestination()
            ),
            select.toInt()
        )
    )
}
