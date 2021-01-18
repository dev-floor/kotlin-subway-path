package subway.view

fun showAdminStation() {
    println("\n## 역 관리 화면\n1. 역 등록\n2. 역 삭제\n3. 역 조회\nB. 돌아가기")
}

fun getRegisterStationName(): String {
    println("\n## 등록할 역 이름을 입력하세요.")
    return readLine()!!
}

fun succeedRegisterStationName() = println("지하철 역이 등록되었습니다.")

fun getDeleteStationName(): String{
    println("\n## 삭제할 역 이름을 입력하세요.")
    return readLine()!!
}

fun succeedDeleteStationName() = println("지하철 역이 삭제되었습니다.")

fun getStationNames() = print("\n## 역 목록")

fun getStationName(name: String) = print(name)
