package subway.domain

data class Station(
    val name: String,
    var isUpwardTerminal: Boolean = false,
    var isDownwardTerminal: Boolean = false
) {

    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2
    }
}
