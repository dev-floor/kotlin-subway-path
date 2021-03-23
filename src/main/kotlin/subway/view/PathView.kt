package subway.view

import subway.domain.dto.PathResponse

const val DISTANCE = "총 거리: "
const val TIME = "총 소요 시간: "

fun pathPage() = println("\n## 경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기")

fun inputDeparture(): String {
    println("\n## 출발역을 입력하세요.")
    return readLine()!!
}

fun inputDestination(): String {
    println("\n## 도착역을 입력하세요.")
    return readLine()!!
}

fun pathResult(pathResponse: PathResponse) {
    print("$INFO_MESSAGE$SEPARATOR_LINE_WITH_STATION")
    print("$INFO_MESSAGE$DISTANCE${pathResponse.distance}$KILOMETER")
    print("$INFO_MESSAGE$TIME${pathResponse.time}$MINUTE")
    print("$INFO_MESSAGE$SEPARATOR_LINE_WITH_STATION")

    pathResponse.route.forEach {
        print("$INFO_MESSAGE$it")
    }
    println()
}
