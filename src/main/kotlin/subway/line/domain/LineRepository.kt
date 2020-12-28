package subway.line.domain

interface LineRepository {
    fun lines(): List<Line>

    fun addLine(line: Line)

    fun deleteLineByName(name: String): Boolean
}
