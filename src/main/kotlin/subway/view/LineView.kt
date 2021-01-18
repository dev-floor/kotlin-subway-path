package subway.view

import subway.repository.LineRepository

fun getRegisterLineName(): String {
    println("\n## 등록할 노선 이름을 입력하세요.")
    return readLine()!!
}

fun succeedRegisterLine() = println("지하철 역이 등록되었습니다.")

fun getDeleteLineName(): String{
    println("\n## 삭제할 역 이름을 입력하세요.")
    return readLine()!!
}

fun succeedDeleteLine() = println("지하철 역이 삭제되었습니다.")

fun showAllLines() {
    print("\n## 노선 목")

    LineRepository.lines().map{
        infoMessage()
        print(it.name)
    }
}