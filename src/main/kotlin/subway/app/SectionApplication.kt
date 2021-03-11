package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.service.DeleteSectionService
import subway.service.RegisterSectionService
import subway.view.getDownwardNameOfSectionToDelete
import subway.view.getDownwardNameOfSectionToRegister
import subway.view.getLineNameOfSectionToDelete
import subway.view.getLineNameOfSectionToRegister
import subway.view.getSectionDistance
import subway.view.getSectionTime
import subway.view.getUpwardNameOfSectionToDelete
import subway.view.getUpwardNameOfSectionToRegister
import subway.view.infoMessage
import subway.view.selectNumber
import subway.view.showAdminSection
import subway.view.succeedDeleteSection
import subway.view.succeedRegisterSection

fun adminSection() {
    showAdminSection()
    val select = selectNumber()
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
