package subway.section.domain

import subway.common.domain.SubwayInitializer
import subway.line.domain.LineInitializer.삼호선
import subway.line.domain.LineInitializer.신분당선
import subway.line.domain.LineInitializer.이호선
import subway.station.domain.StationInitializer.강남역
import subway.station.domain.StationInitializer.교대역
import subway.station.domain.StationInitializer.남부터미널역
import subway.station.domain.StationInitializer.매봉역
import subway.station.domain.StationInitializer.양재시민의숲역
import subway.station.domain.StationInitializer.양재역
import subway.station.domain.StationInitializer.역삼역

@Suppress("NonAsciiCharacters")
object SectionInitializer : SubwayInitializer<SectionRepository> {
    private val 이호선_교대역_강남역 = Section(이호선, 교대역, 강남역, 2, 3)
    private val 이호선_강남역_역삼역 = Section(이호선, 강남역, 역삼역, 2, 3)
    private val 삼호선_교대역_남부터미널역 = Section(삼호선, 교대역, 남부터미널역, 3, 2)
    private val 삼호선_남부터미널역_양재역 = Section(삼호선, 남부터미널역, 양재역, 6, 5)
    private val 삼호선_양재역_매봉역 = Section(삼호선, 양재역, 매봉역, 1, 1)
    private val 신분당선_강남역_양재역 = Section(신분당선, 강남역, 양재역, 2, 8)
    private val 신분당선_양재역_양재시민의숲역 = Section(신분당선, 양재역, 양재시민의숲역, 10, 3)

    override fun initialize(repository: SectionRepository) =
        repository.saveAll(
            이호선_교대역_강남역,
            이호선_강남역_역삼역,
            삼호선_교대역_남부터미널역,
            삼호선_남부터미널역_양재역,
            삼호선_양재역_매봉역,
            신분당선_강남역_양재역,
            신분당선_양재역_양재시민의숲역
        )
}
