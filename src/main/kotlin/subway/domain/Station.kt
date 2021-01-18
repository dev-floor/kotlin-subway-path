package subway.domain

class Station(val name: String) {

    init {
        require(NAME_MAX_LENGTH <= name.length)
    }

    companion object{
        const val NAME_MAX_LENGTH = 2
    }
}
