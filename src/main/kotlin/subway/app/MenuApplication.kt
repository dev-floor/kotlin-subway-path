package subway.app

import subway.service.RouteMapService
import subway.view.inputSelect
import subway.view.mainPage
import subway.view.routeMap

const val MENU_ONE = 1
const val MENU_TWO = 2
const val MENU_THREE = 3
const val MENU_FOUR = 4
const val MENU_FIVE = 5

const val QUIT = "Q"
const val BACK = "B"

fun startApp() {
    while (true) {
        mainPage()
        val select = inputSelect()

        if (select == QUIT) break

        when (select.toInt()) {
            MENU_ONE -> adminStation()
            MENU_TWO -> adminLine()
            MENU_THREE -> adminSection()
            MENU_FOUR -> routeMap(RouteMapService.routeMap())
            MENU_FIVE -> path()
        }
    }
}
