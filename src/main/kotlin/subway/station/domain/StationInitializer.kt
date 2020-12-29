package subway.station.domain

import subway.common.domain.SubwayInitializer

@Suppress("NonAsciiCharacters")
object StationInitializer : SubwayInitializer<StationRepository> {
    val 교대역 = Station("교대역")
    val 강남역 = Station("강남역")
    val 역삼역 = Station("역삼역")
    val 남부터미널역 = Station("남부터미널역")
    val 양재역 = Station("양재역")
    val 양재시민의숲역 = Station("양재시민의숲역")
    val 매봉역 = Station("매봉역")

    override fun initialize(repository: StationRepository) =
        repository.saveAll(교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역)
}
