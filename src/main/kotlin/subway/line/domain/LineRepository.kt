package subway.line.domain

interface LineRepository {
    fun findAll(): List<Line>

    fun save(line: Line)

    fun saveAll(vararg lines: Line)

    fun deleteByName(name: String): Boolean
}
