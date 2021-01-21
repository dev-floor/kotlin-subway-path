package subway.view

fun showAdminSection() = println("\n## 구간 관리 화면\n1. 구간 등록\n2. 구간 삭제\nB. 돌아가기")

fun getLineNameOfSectionToRegister(): String {
    println("\n## 노선을 입력하세요.")
    return readLine()!!
}

fun getUpwardNameOfSectionToRegister(): String {
    println("\n## 상행역을 입력하세요.")
    return readLine()!!
}

fun getDownwardNameOfSectionToRegister(): String {
    println("\n## 하행역을 입력하세요.")
    return readLine()!!
}

fun getSectionDistance(): Int {
    println("\n## 구간 거리(km)를 입력하세요.")
    return readLine()!!.toInt()
}

fun getSectionTime(): Int {
    println("\n## 소요 시간(분)을 입력하세요.")
    return readLine()!!.toInt()
}

fun succeedRegisterSection() = println("구간이 등록되었습니다.")