package subway.repository

import subway.domain.Line

object LineRepository {
    private val lines = mutableListOf<Line>()

    private fun lines() = lines.toList()

    fun add(line: Line) = lines.add(line)

    fun deleteByName(name: String) = lines.removeIf { it.match(name) }

    fun existsByName(name: String): Boolean = lines().any { it.match(name) }

    fun findByName(name: String) = lines().first { it.match(name) }

    fun findAll() = lines()
}
