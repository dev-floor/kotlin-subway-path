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
    StationRepository.addStation(Station("교대역"))
    StationRepository.addStation(Station("강남역"))
    StationRepository.addStation(Station("역삼역"))
    StationRepository.addStation(Station("남부터미널역"))
    StationRepository.addStation(Station("양재역"))
    StationRepository.addStation(Station("양재시민의숲역"))
    StationRepository.addStation(Station("매봉역"))
}

fun initLine() {
    LineRepository.addLine(Line("2호선"))
    LineRepository.addLine(Line("3호선"))
    LineRepository.addLine(Line("신분당선"))
}

fun initSection() {
    SectionRepository.addSection(Section(Line("2호선"), Station("교대역"), Station("강남역"), 3, 2))
    SectionRepository.addSection(Section(Line("2호선"), Station("강남역"), Station("역삼역"), 3, 2))
    SectionRepository.addSection(Section(Line("3호선"), Station("교대역"), Station("남부터미널역"), 2, 3))
    SectionRepository.addSection(Section(Line("3호선"), Station("남부터미널역"), Station("양재역"), 5, 6))
    SectionRepository.addSection(Section(Line("3호선"), Station("양재역"), Station("매봉역"), 1, 1))
    SectionRepository.addSection(Section(Line("신분당선"), Station("강남역"), Station("양재역"), 8, 2))
    SectionRepository.addSection(Section(Line("신분당선"), Station("양재역"), Station("양재시민의숲역"), 3, 10))
}

fun initTerminal() {
    SectionRepository.sections()
        .map{ }
}