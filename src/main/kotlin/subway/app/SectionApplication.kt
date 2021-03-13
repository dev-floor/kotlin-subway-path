package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.service.DeleteSectionService
import subway.service.RegisterSectionService
import subway.view.deletedSection
import subway.view.infoMessage
import subway.view.inputDownwardNameOfSectionToDelete
import subway.view.inputDownwardNameOfSectionToRegister
import subway.view.inputLineNameOfSectionToDelete
import subway.view.inputLineNameOfSectionToRegister
import subway.view.inputSectionDistance
import subway.view.inputSectionTime
import subway.view.inputSelect
import subway.view.inputUpwardNameOfSectionToDelete
import subway.view.inputUpwardNameOfSectionToRegister
import subway.view.registeredSection
import subway.view.sectionPage

fun adminSection() {
    sectionPage()
    val select = inputSelect()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            RegisterSectionService(
                Section(
                    line = Line(inputLineNameOfSectionToRegister()),
                    upwardStation = Station(inputUpwardNameOfSectionToRegister()),
                    downwardStation = Station(inputDownwardNameOfSectionToRegister()),
                    distance = inputSectionDistance(),
                    time = inputSectionTime()
                )
            ).register()
            infoMessage()
            registeredSection()
        }
        MENU_TWO -> {
            DeleteSectionService(
                line = Line(inputLineNameOfSectionToDelete()),
                upwardStation = Station(inputUpwardNameOfSectionToDelete()),
                downwardStation = Station(inputDownwardNameOfSectionToDelete())
            ).delete()
            infoMessage()
            deletedSection()
        }
    }
}
