package subway.domain

class Line(val name: String) {
    init {
        require(LINE_NAME_MAX_LENGTH <= name.length)
    }

    fun match(name: String): Boolean = this.name == name

    companion object {
        const val LINE_NAME_MAX_LENGTH = 2
    }
}
