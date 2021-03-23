package subway.domain

class Station(
    val name: String,
    var isUpwardTerminal: Boolean = false,
    var isDownwardTerminal: Boolean = false
) {
    init {
        require(STATION_NAME_MAX_LENGTH <= name.length)
    }

    fun match(name: String): Boolean = name == this.name

    companion object {
        const val STATION_NAME_MAX_LENGTH = 2

        fun toEntity(
            name: String,
            isUpwardTerminal: Boolean = false,
            isDownwardTerminal: Boolean = false
        ) = Station(
            name,
            isUpwardTerminal,
            isDownwardTerminal
        )
    }
}
