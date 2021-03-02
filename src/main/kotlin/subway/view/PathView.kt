package subway.view

import org.jgrapht.GraphPath
import org.jgrapht.graph.DefaultWeightedEdge
import subway.domain.Station

fun showCheckPath() = println("\n## 경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기")

fun getDeparture(): String {
    println("\n## 출발역을 입력하세요.")
    return readLine()!!
}

fun getDestination(): String {
    println("\n## 도착역을 입력하세요.")
    return readLine()!!
}

fun showShortestPathResult(stations: List<String>) {

    println(stations)
}
