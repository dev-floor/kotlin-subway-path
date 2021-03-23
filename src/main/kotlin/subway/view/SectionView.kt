package subway.view

fun sectionPage() = println("\n## 구간 관리 화면\n1. 구간 등록\n2. 구간 삭제\nB. 돌아가기")

fun inputLineNameOfSectionToRegister(): String {
    println("\n## 노선을 입력하세요.")
    return readLine()!!
}

fun inputUpwardNameOfSectionToRegister(): String {
    println("\n## 상행역을 입력하세요.")
    return readLine()!!
}

fun inputDownwardNameOfSectionToRegister(): String {
    println("\n## 하행역을 입력하세요.")
    return readLine()!!
}

fun inputSectionDistance(): Int {
    println("\n## 구간 거리(km)를 입력하세요.")
    return readLine()!!.toInt()
}

fun inputSectionTime(): Int {
    println("\n## 소요 시간(분)을 입력하세요.")
    return readLine()!!.toInt()
}

fun registeredSection() = println("구간이 등록되었습니다.")

fun inputLineNameOfSectionToDelete(): String {
    println("\n## 삭제할 구간의 노선을 입력하세요.")
    return readLine()!!
}

fun inputUpwardNameOfSectionToDelete(): String {
    println("\n## 삭제할 구간의 상행역을 입력하세요.")
    return readLine()!!
}

fun inputDownwardNameOfSectionToDelete(): String {
    println("\n## 삭제할 구간의 하행역을 입력하세요.")
    return readLine()!!
}

fun deletedSection() = println("구간이 삭제되었습니다.")
