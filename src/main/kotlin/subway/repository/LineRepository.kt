package subway.domain

object LineRepository {
    private val lines = mutableListOf<Line>()

    fun lines() = lines.toList()

    fun addLine(line: Line) {
        lines.add(line)
    }

    fun deleteLineByName(name: String) = lines.removeIf { it.name == name }
}
