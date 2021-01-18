package subway.view

import subway.repository.LineRepository

fun showAdminLine() = println("\n## 노선 관리 화면\n1. 노선 등록\n2. 노선 삭제\n3. 노선 조회\nB. 돌아가기")

fun getRegisterLineName(): String {
    println("\n## 등록할 노선 이름을 입력하세요.")
    return readLine()!!
}

fun succeedRegisterLine() = println("지하철 노선이 등록되었습니다.")

fun getDeleteLineName(): String {
    println("\n## 삭제할 노선 이름을 입력하세요.")
    return readLine()!!
}

fun succeedDeleteLine() = println("지하철 노선이 삭제되었습니다.")

fun showAllLines() {
    print("\n## 노선 목록")
    LineRepository.lines().map {
        infoMessage()
        print(it.name)
    }
    println()
}
