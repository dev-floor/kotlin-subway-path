package subway.view

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