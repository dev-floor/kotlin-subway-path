package subway.view

fun linePage() = println("\n## 노선 관리 화면\n1. 노선 등록\n2. 노선 삭제\n3. 노선 조회\nB. 돌아가기")

fun inputLineNameToRegister(): String {
    println("\n## 등록할 노선 이름을 입력하세요.")
    return readLine()!!
}

fun inputUpwardStationName(): String {
    println("\n## 등록할 노선의 상행 종점역 이름을 입력하세요.")
    return readLine()!!
}

fun inputDownwardStationName(): String {
    println("\n## 등록할 노선의 하행 종점역 이름을 입력하세요.")
    return readLine()!!
}

fun inputDistance(): Int {
    println("\n## 구간 거리(km)를 입력하세요.")
    return readLine()!!.toInt()
}

fun inputTime(): Int {
    println("\n## 소요 시간(분)을 입력하세요.")
    return readLine()!!.toInt()
}

fun registeredLine() = println("지하철 노선이 등록되었습니다.")

fun inputLineNameToDelete(): String {
    println("\n## 삭제할 노선 이름을 입력하세요.")
    return readLine()!!
}

fun deletedLine() = println("지하철 노선이 삭제되었습니다.")

fun allLines(lineNames: List<String>) {
    print("\n## 노선 목록")
    lineNames
        .forEach {
            infoMessage()
            print(it)
        }
    println()
}
