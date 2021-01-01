package subway

import subway.ui.common.NUMBER_FORMAT_MESSAGE
import subway.ui.common.ViewNavigation
import subway.ui.common.generateErrorMessage
import subway.ui.main.MainView

fun main() {
    ViewNavigation.init(MainView())
    runNavigation()
}

private fun runNavigation() {
    do {
        try {
            ViewNavigation.run()
        } catch (e: NumberFormatException) {
            println(generateErrorMessage(NUMBER_FORMAT_MESSAGE))
        } catch (e: IllegalArgumentException) {
            println(generateErrorMessage(e.localizedMessage))
        }
    } while (!ViewNavigation.isEmpty)
}
