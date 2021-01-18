package subway.domain

class Station(val name: String) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    companion object{
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
