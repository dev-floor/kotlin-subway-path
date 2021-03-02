package subway.repository

import subway.domain.Line

const val SEPARATOR_LINE_WITH_STATION = "---"

const val SEPARATOR_EACH_LINE = "\n"

object LineRepository {
    private val lines = mutableListOf<Line>()

    fun lines() = lines.toList()

    fun add(line: Line) = lines.add(line)

    fun deleteByName(name: String) = lines.removeIf { it.name == name }

    fun existsByName(name: String): Boolean = lines().any { it.name == name }

    fun findByName(name: String) = lines().first { it.name == name }

    fun allLinesInfo(): List<String> {
        val printMessage = mutableListOf<String>()
        lines().forEach { it ->
            printMessage.add(it.name)
            printMessage.add(SEPARATOR_LINE_WITH_STATION)
            SectionRepository.allSectionsInLine(it.name, mutableListOf())
                .forEach { printMessage.add(it) }
            printMessage.add(SEPARATOR_EACH_LINE)
        }
        return printMessage
    }
}
