package subway.repository

import subway.domain.Line

object LineRepository {
    private val lines = mutableListOf<Line>()

    private fun lines() = lines.toList()

    fun add(line: Line) = lines.add(line)

    fun deleteByName(name: String) = lines.removeIf { it.name == name }

    fun existsByName(name: String): Boolean = lines().any { it.name == name }

    fun findByName(name: String) = lines().first { it.name == name }

    fun findAll() = lines()
}
