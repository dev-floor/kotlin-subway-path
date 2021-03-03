package subway.domain

data class Section(
    val line: Line,
    val upwardStation: Station,
    val downwardStation: Station,
    var time: Int? = 3,
    var distance: Int? = 2
) {
}
