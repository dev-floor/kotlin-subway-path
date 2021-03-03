package subway.init

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

fun init() {
    initStation()
    initLine()
    initSection()
}

fun initStation() {
    StationRepository.add(Station("교대역"))
    StationRepository.add(Station("강남역"))
    StationRepository.add(Station("역삼역"))
    StationRepository.add(Station("남부터미널역"))
    StationRepository.add(Station("양재역"))
    StationRepository.add(Station("양재시민의숲역"))
    StationRepository.add(Station("매봉역"))
}

fun initLine() {
    LineRepository.add(Line("2호선"))
    LineRepository.add(Line("3호선"))
    LineRepository.add(Line("신분당선"))
}

fun initSection() {
    SectionRepository.add(Section(Line("2호선"), Station("교대역", true), Station("강남역"), 3, 2))
    SectionRepository.add(
        Section(
            Line("2호선"), Station("강남역"),
            Station("역삼역", isUpwardTerminal = false, isDownwardTerminal = true), 3, 2
        )
    )
    SectionRepository.add(Section(Line("3호선"), Station("교대역", true), Station("남부터미널역"), 2, 3))
    SectionRepository.add(Section(Line("3호선"), Station("남부터미널역"), Station("양재역"), 5, 6))
    SectionRepository.add(
        Section(
            Line("3호선"),
            Station("양재역"), Station("매봉역", isUpwardTerminal = false, isDownwardTerminal = true), 1, 1
        )
    )
    SectionRepository.add(Section(Line("신분당선"), Station("강남역", true), Station("양재역"), 8, 2))
    SectionRepository.add(
        Section(
            Line("신분당선"), Station("양재역"),
            Station("양재시민의숲역", isUpwardTerminal = false, isDownwardTerminal = true), 3, 10
        )
    )
}
