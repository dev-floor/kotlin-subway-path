package subway.domain

class Line(val name: String){

    init{
        require(LINE_NAME_MAX_LENGTH <= name.length)
    }

    companion object{
        const val LINE_NAME_MAX_LENGTH = 2
    }
}
