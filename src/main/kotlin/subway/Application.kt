package subway

import subway.domain.Line
import subway.domain.LineRepository.addLine

import subway.domain.Station
import subway.domain.StationRepository.addStation
import subway.view.mainPage


fun main() {
    init()
    mainPage()
}

fun init() {
    addStation(Station("교대역"))
    addStation(Station("강남역"))
    addStation(Station("역삼역"))
    addStation(Station("남부터미널역"))
    addStation(Station("양재역"))
    addStation(Station("양재시민의숲역"))
    addStation(Station("매봉역"))
    addLine(Line("2호선"))
    addLine(Line("3호선"))
    addLine(Line("4호선"))
}