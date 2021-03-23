package subway.app

import subway.domain.Line
import subway.domain.Station
import subway.dto.SectionRequest
import subway.service.SectionService
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
            SectionService.register(
                SectionRequest(
                    line = Line(inputLineNameOfSectionToRegister()),
                    upwardStation = Station(inputUpwardNameOfSectionToRegister()),
                    downwardStation = Station(inputDownwardNameOfSectionToRegister()),
                    distance = inputSectionDistance(),
                    time = inputSectionTime()
                )
            )
            infoMessage()
            registeredSection()
        }
        MENU_TWO -> {
            SectionService.delete(
                SectionRequest(
                    line = Line(inputLineNameOfSectionToDelete()),
                    upwardStation = Station(inputUpwardNameOfSectionToDelete()),
                    downwardStation = Station(inputDownwardNameOfSectionToDelete())
                )
            )
            infoMessage()
            deletedSection()
        }
    }
}
