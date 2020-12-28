package subway.line.infra

import subway.line.domain.Line
import subway.line.domain.LineRepository

class InMemoryLineRepository : LineRepository {
    override fun findAll() = LINES.toList()

    override fun save(line: Line) {
        LINES.add(line)
    }

    override fun saveAll(vararg lines: Line) {
        LINES.addAll(lines)
    }

    override fun deleteByName(name: String) = LINES.removeIf { it.name == name }

    companion object {
        private val LINES = mutableListOf<Line>()
    }
}
