package subway.common.domain

import subway.common.exception.INVALID_NAME_MESSAGE

data class Name(val name: String) : Comparable<Name> {
    init {
        require(name.length >= MIN_LENGTH) { INVALID_NAME_MESSAGE }
    }

    fun match(name: String) = this.name == name

    companion object {
        const val MIN_LENGTH = 2
    }

    override fun compareTo(other: Name) = name.compareTo(other.name)
}
