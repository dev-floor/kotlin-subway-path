package subway.app

import subway.init.*
import subway.view.selectMessage
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
        select = selectMessage()
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