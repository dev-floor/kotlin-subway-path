package subway.repository

import subway.domain.Line

const val SEPARATOR_LINE_WITH_STATION = "---"

const val SEPARATOR_EACH_LINE = "\n"

object LineRepository {
    private val lines = mutableListOf<Line>()

    fun lines() = lines.toList()

    fun addLine(line: Line) = lines.add(line)

    fun deleteLineByName(name: String) = lines.removeIf { it.name == name }

    fun existLineByName(name: String): Boolean = lines().any { it.name == name }

    fun wholeLineInformation(): List<String> {
        val wholeTrack = mutableListOf<String>()
        lines().forEach { it ->
            wholeTrack.add(it.name)
            wholeTrack.add(SEPARATOR_LINE_WITH_STATION)
            val wholeTrackInLine = SectionRepository.wholeStationsInSection(it.name, mutableListOf())
            wholeTrackInLine.forEach { wholeTrack.add(it) }
            wholeTrack.add(SEPARATOR_EACH_LINE)
        }
        return wholeTrack
    }
}
