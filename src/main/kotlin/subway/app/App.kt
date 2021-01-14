package subway.app

import subway.init.*
import subway.domain.Station
import subway.view.getStationName
import subway.view.selectMenu
import subway.view.showAdminStation
import subway.view.showMainPage

const val QUIT = "Q"
const val BACK = "B"
var select: String = ""

fun startApp() {
    mainPage()
}

fun mainPage() {
    while(true){
        showMainPage()
        select = selectMenu()
        if(select !== QUIT){
            when(select.toInt()) {
                ONE -> adminStation()
                TWO -> adminLine()
                THREE -> adminSection()
                FOUR -> showWholeTrainRoute()
                FIVE -> checkRoute()
            }
            continue
        }
        break
    }
}

fun adminStation() {
    showAdminStation()
    select = selectMenu()
    if(select !== BACK){

        when(select.toInt()){
            ONE -> Station(getStationName()).addStation()
            TWO -> Station(getStationName()).deleteStation()
//            THREE ->
        }
    }
}

fun adminLine() {
    TODO("Not yet implemented")
}

fun adminSection() {
    TODO("Not yet implemented")
}

fun showWholeTrainRoute() {
    TODO("Not yet implemented")
}

fun checkRoute() {
    TODO("Not yet implemented")
}