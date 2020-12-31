package subway.line.domain

interface LineRepository {
    fun save(line: Line)

    fun saveAll(vararg lines: Line)

    fun saveAll(lines: List<Line>)

    fun findAll(): List<Line>

    fun exists(line: Line): Boolean

    fun existsByName(name: String): Boolean

    fun deleteByName(name: String): Boolean
}
