package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.service.DeleteLineService
import subway.service.DeleteSectionService
import subway.service.DeleteStationService
import subway.service.RegisterLineService
import subway.service.RegisterSectionService
import subway.service.RegisterStationService
import subway.service.ShowRouteMapService
import subway.view.getDistance
import subway.view.getDownwardNameOfSectionToDelete
import subway.view.getDownwardNameOfSectionToRegister
import subway.view.getDownwardStationName
import subway.view.getLineNameOfSectionToDelete
import subway.view.getLineNameOfSectionToRegister
import subway.view.getLineNameToDelete
import subway.view.getLineNameToRegister
import subway.view.getSectionDistance
import subway.view.getSectionTime
import subway.view.getStationNameToDelete
import subway.view.getStationNameToRegister
import subway.view.getTime
import subway.view.getUpwardNameOfSectionToDelete
import subway.view.getUpwardNameOfSectionToRegister
import subway.view.getUpwardStationName
import subway.view.infoMessage
import subway.view.selectNumber
import subway.view.showAdminLine
import subway.view.showAdminSection
import subway.view.showAdminStation
import subway.view.showAllLines
import subway.view.showAllStations
import subway.view.showCheckPath
import subway.view.showMainPage
import subway.view.showRouteMap
import subway.view.succeedDeleteLine
import subway.view.succeedDeleteSection
import subway.view.succeedDeleteStation
import subway.view.succeedRegisterLine
import subway.view.succeedRegisterSection
import subway.view.succeedRegisterStation

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
            MENU_FOUR -> routeMap()
            MENU_FIVE -> checkPath()
        }
    }
}

fun routeMap() {
    showRouteMap(ShowRouteMapService().routeMap())
}

fun adminStation() {
    showAdminStation()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            val name = getStationNameToRegister()
            RegisterStationService(Station(name)).register()
            infoMessage()
            succeedRegisterStation()
        }
        MENU_TWO -> {
            val name = getStationNameToDelete()
            DeleteStationService(name).delete()
            infoMessage()
            succeedDeleteStation()
        }
        MENU_THREE -> showAllStations()
    }
}

fun adminLine() {
    showAdminLine()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            val line = Line(getLineNameToRegister())
            RegisterLineService(line).register()
            RegisterSectionService(
                Section(
                    line = line,
                    upwardStation = Station(getUpwardStationName()),
                    downwardStation = Station(getDownwardStationName()),
                    distance = getDistance(),
                    time = getTime()
                )
            ).register()
            infoMessage()
            succeedRegisterLine()
        }
        MENU_TWO -> {
            DeleteLineService(getLineNameToDelete()).delete()
            infoMessage()
            succeedDeleteLine()
        }
        MENU_THREE -> showAllLines()
    }
}

fun adminSection() {
    showAdminSection()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            RegisterSectionService(
                Section(
                    line = Line(getLineNameOfSectionToRegister()),
                    upwardStation = Station(getUpwardNameOfSectionToRegister()),
                    downwardStation = Station(getDownwardNameOfSectionToRegister()),
                    distance = getSectionDistance(),
                    time = getSectionTime()
                )
            ).register()
            infoMessage()
            succeedRegisterSection()
        }
        MENU_TWO -> {
            DeleteSectionService(
                line = Line(getLineNameOfSectionToDelete()),
                upwardStation = Station(getUpwardNameOfSectionToDelete()),
                downwardStation = Station(getDownwardNameOfSectionToDelete())
            ).delete()
            infoMessage()
            succeedDeleteSection()
        }
    }
}

fun checkPath() {
    showCheckPath()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> shortestPath()
        MENU_TWO -> minimumTime()
    }
}
