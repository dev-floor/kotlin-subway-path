package subway.line.infra

import subway.line.domain.Line
import subway.line.domain.LineRepository

class InMemoryLineRepository : LineRepository {
    private val lines: MutableList<Line> = mutableListOf()

    override fun save(line: Line) {
        lines.add(line)
    }

    override fun saveAll(vararg lines: Line) {
        this.lines.addAll(lines)
    }

    override fun saveAll(lines: List<Line>) {
        this.lines.addAll(lines)
    }

    override fun findAll() = lines.toList()

    override fun existsByName(name: String) = lines.any { it.match(name) }

    override fun deleteByName(name: String) = lines.removeIf { it.name.match(name) }
}
