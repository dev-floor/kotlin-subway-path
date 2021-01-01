package subway.ui.path

import subway.common.application.ServiceHandler.PATH_SERVICE
import subway.path.application.PathRequest
import subway.ui.common.SEPARATE_LINE
import subway.ui.common.View
import subway.ui.common.ViewNavigation
import subway.ui.common.generateInfoMessage

class PathShowView(private val pathType: String) : View {
    override fun render() {
        println("\n## 경로의 출발역을 입력하세요.")
        val departureName = readLine()?.trim() ?: throw AssertionError()

        println("\n## 경로의 도착역을 입력하세요.")
        val destinationName = readLine()?.trim() ?: throw AssertionError()

        val request = PathRequest(pathType, departureName, destinationName)
        val (stations, distance, duration) = PATH_SERVICE.show(request)

        renderPathResult(distance, duration, stations)

        ViewNavigation.goToFirst()
    }

    private fun renderPathResult(
        distance: Long,
        duration: Long,
        stations: List<String>,
    ) {
        println("\n## 조회 결과")
        println(SEPARATE_LINE)
        println(generateInfoMessage("총 경로 거리: ${distance}km"))
        println(generateInfoMessage("총 소요 시간: ${duration}분"))
        println(SEPARATE_LINE)
        stations.forEach { println(generateInfoMessage(it)) }
    }
}
