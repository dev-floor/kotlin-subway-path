package subway.repository

import subway.domain.Section

object SectionRepository {

    private val sections = mutableListOf<Section>()

    fun sections() = sections.toList()

    fun addSection(section: Section) {
        sections.add(section)
    }

    fun existDownwardByName(name: String): Boolean = sections().any { it.downwardStation.name == name }

    fun existUpwardByName(name: String): Boolean = sections().any { it.upwardStation.name == name }

    fun findUpwardNameByDownwardName(name: String): String = sections
        .filter { it.downwardStation.name == name }
        .map { it -> it.upwardStation.name }
        .toString()

    fun findDownwardNameByUpwardName(name: String): String = sections
        .filter { it.upwardStation.name == name }
        .map { it -> it.downwardStation.name }
        .toString()

    fun existUpperName(name: String): Boolean = sections
            .any{ it.downwardStation.name == name }

    fun existDownerName(name: String): Boolean = sections
            .any{ it.upwardStation.name == name }

    fun existStationInLine(name: String) = sections()
                .any{ it.downwardStation.name == name || it.upwardStation.name == name }

    fun deleteSection(lineName: String, upwardName:String, downwardName: String) = sections
            .removeIf { it.line.name == lineName
                    && it.upwardStation.name ==upwardName
                    && it.downwardStation.name == downwardName}

    fun stationCountInSection(name: String) = sections().count { it.line.name == name}

    fun continuousStation(upwardName:String, downwardName: String) = sections()
            .any{it.downwardStation.name == downwardName && it.upwardStation.name == upwardName}
}
