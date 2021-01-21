package subway.view

fun getLineNameOfSectionToDelete(): String {
    println("\n## 삭제할 구간의 노선을 입력하세요.")
    return readLine()!!
}

fun getUpwardNameOfSectionToDelete(): String {
    println("\n## 삭제할 구간의 상행역을 입력하세요.")
    return readLine()!!
}

fun getDownwardNameOfSectionToDelete(): String {
    println("\n## 삭제할 구간의 하행역을 입력하세요.")
    return readLine()!!
}

fun succeedDeleteSection() = println("구간이 삭제되었습니다.")