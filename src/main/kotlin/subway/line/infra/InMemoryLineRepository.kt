package subway.line.infra

import subway.line.domain.Line
import subway.line.domain.LineRepository

class InMemoryLineRepository : LineRepository {
    override fun lines() = LINES.toList()

    override fun addLine(line: Line) {
        LINES.add(line)
    }

    override fun deleteLineByName(name: String) = LINES.removeIf { it.name == name }

    companion object {
        private val LINES = mutableListOf<Line>()
    }
}
