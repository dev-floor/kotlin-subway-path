package subway.common.domain

interface SubwayInitializer<T> {
    fun initialize(repository: T)
}
