package subway.domain

class Section(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
    var time: Int? = TIME_DEFAULT,
    var distance: Int? = DISTANCE_DEFAULT
) {
    fun matchUpward(name: String): Boolean = name == this.upwardStation.name

    fun matchDownward(name: String): Boolean = name == this.downwardStation.name

    companion object {
        const val TIME_DEFAULT = 3
        const val DISTANCE_DEFAULT = 2
    }
}
