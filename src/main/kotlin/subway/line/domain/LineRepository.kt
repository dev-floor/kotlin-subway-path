package subway.line.domain

interface LineRepository {
    fun findAll(): List<Line>

    fun save(line: Line)

    fun saveAll(vararg lines: Line)

    fun saveAll(lines: List<Line>)

    fun deleteByName(name: String): Boolean
}
