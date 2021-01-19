package subway.view

fun showAdminSection() = println("\n## 구간 관리 화면\n1. 구간 등록\n2. 구간 삭제\nB. 돌아가기")

fun getUpwardStationName(): String {
    println("\n## 등록할 노선의 상행 종점역 이름을 입력하세요.")
    return readLine()!!
}

fun getDownwardStationName(): String {
    println("\n## 등록할 노선의 하행 종점역 이름을 입력하세요.")
    return readLine()!!
}

fun getDistance(): Int {
    println("\n## 구간 거리(km)를 입력하세요.")
    return readLine()!!.toInt()
}

fun getTime(): Int {
    println("\n## 소요 시간(분)을 입력하세요.")
    return readLine()!!.toInt()
}
