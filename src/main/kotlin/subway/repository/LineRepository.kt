package subway.repository

import subway.domain.Line

object LineRepository {
    private val lines = mutableListOf<Line>()

    fun lines() = lines.toList()

    fun addLine(line: Line) = lines.add(line)

    fun deleteLineByName(name: String) = lines.removeIf { it.name == name }

    fun existLineByName(name: String): Boolean = lines().any { it.name == name }
}
