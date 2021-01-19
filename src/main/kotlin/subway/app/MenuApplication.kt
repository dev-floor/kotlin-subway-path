package subway.app

import subway.view.*

const val MENU_ONE = 1
const val MENU_TWO = 2
const val MENU_THREE = 3
const val MENU_FOUR = 4
const val MENU_FIVE = 5

const val QUIT = "Q"
const val BACK = "B"

var select: String = ""

fun startApp() {
    while (true) {
        showMainPage()
        select = selectNumber()

        if (select == QUIT) break

        when (select.toInt()) {
            MENU_ONE -> adminStation()
            MENU_TWO -> adminLine()
            MENU_THREE -> adminSection()
            MENU_FOUR -> showWholeTrainRoute()
            MENU_FIVE -> checkRoute()
        }
    }
}

fun adminStation() {
    showAdminStation()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> registerStation()
        MENU_TWO -> deleteStation()
        MENU_THREE -> showAllStations()
    }
}

fun adminLine() {
    showAdminLine()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> registerLine()
        MENU_TWO -> deleteLine()
        MENU_THREE -> showAllLines()
    }
}

fun adminSection() {
    showAdminSection()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> registerSection()
        MENU_TWO -> deleteSection()
    }
}

fun showWholeTrainRoute() {
    TODO("Not yet implemented")
}

fun checkRoute() {
    TODO("Not yet implemented")
}
