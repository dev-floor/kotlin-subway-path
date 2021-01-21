package subway.view

import subway.repository.LineRepository
import subway.repository.SEPARATOR_EACH_LINE

const val INFO_MESSAGE = "\n[INFO] "
const val ERROR_MESSAGE = "\n[ERROR] "

fun showMainPage() = println("\n## 메인 화면\n1. 역 관리\n2. 노선 관리\n3. 구간 관리\n4. 지하철 노선도 출력\n5. 경로 조회\nQ. 종료")

fun infoMessage() = print(INFO_MESSAGE)

fun errorMessage() = print(ERROR_MESSAGE)

fun selectNumber(): String {
    println("\n## 원하는 기능을 선택하세요.")
    return readLine()!!
}

fun showWholeTrack() {
    val wholeTrack = LineRepository.wholeLineInformation()
    print("\n ## 지하철 노선도")
    wholeTrack
        .forEach{
            if(it !== SEPARATOR_EACH_LINE)
                print(INFO_MESSAGE)
            print(it)
        }
}
