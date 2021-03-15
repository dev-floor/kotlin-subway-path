package subway.domain

class Section(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
    var time: Int? = TIME_DEFAULT,
    var distance: Int? = DISTANCE_DEFAULT
) {
    fun match(lineName: String, upwardName: String, downwardName: String) =
        this.line.match(lineName) &&
            this.upwardStation.match(upwardName) &&
            this.downwardStation.match(downwardName)

    fun matchUpward(name: String): Boolean = name == this.upwardStation.name

    fun matchDownward(name: String): Boolean = name == this.downwardStation.name

    fun matchLineAndUpward(lineName: String, stationName: String): Boolean =
        this.line.match(lineName) && this.upwardStation.match(stationName)

    fun matchLineAndDownward(lineName: String, stationName: String): Boolean =
        this.line.match(lineName) && this.downwardStation.match(stationName)

    companion object {
        const val TIME_DEFAULT = 3
        const val DISTANCE_DEFAULT = 2
    }
}
