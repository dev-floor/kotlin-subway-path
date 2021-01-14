package subway.view

fun showAdminStation() {
    println("## 역 관리 화면\n1. 역 등록\n2. 역 삭제\n3. 역 조회\nB. 돌아가기")
}

fun getStationName(): String {
    return readLine()!!
}

fun getDeleteStationName(): String{
    return readLine()!!
}