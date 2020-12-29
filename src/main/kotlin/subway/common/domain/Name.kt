package subway.common.domain

data class Name(val name: String) {
    init {
        require(name.length >= MIN_LENGTH) { "이름은 2글자 이상만 가능합니다." }
    }

    companion object {
        private const val MIN_LENGTH = 2
    }
}
