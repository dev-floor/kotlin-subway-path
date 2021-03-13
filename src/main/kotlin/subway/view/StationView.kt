package subway.view

fun stationPage() = println("\n## 역 관리 화면\n1. 역 등록\n2. 역 삭제\n3. 역 조회\nB. 돌아가기")

fun allStations(stationNames: List<String>) {
    print("\n## 역 목록")
    stationNames.forEach {
        infoMessage()
        print(it)
    }
    println()
}

fun deletedStation() = println("지하철 역이 삭제되었습니다.")

fun inputStationNameToDelete(): String {
    println("\n## 삭제할 역 이름을 입력하세요.")
    return readLine()!!
}

fun inputStationNameToRegister(): String {
    println("\n## 등록할 역 이름을 입력하세요.")
    return readLine()!!
}

fun registeredStation() = println("지하철 역이 등록되었습니다.")
