package subway.view

fun showMainPage() = println("\n## 메인 화면\n1. 역 관리\n2. 노선 관리\n3. 구간 관리\n4. 지하철 노선도 출력\n5. 경로 조회\nQ. 종료")

fun infoMessage() = print("\n[INFO] ")

fun errorMessage() = print("\n[ERROR] ")

fun selectNumber(): String {
    println("\n## 원하는 기능을 선택하세요.")
    return readLine()!!
}
