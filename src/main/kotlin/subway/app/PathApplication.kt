package subway.app

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.WeightedMultigraph
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.getDeparture
import subway.view.getDestination
import subway.view.showShortestPathResult

fun shortestPath() {
    val graph = WeightedMultigraph<String, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

    val departure = StationRepository.findByName(getDeparture())
    val destination = StationRepository.findByName(getDestination())

    val stations = StationRepository.stations()
    val sections = SectionRepository.findAll()

    stations.map { graph.addVertex(it.name) }
    sections.map {
        graph.setEdgeWeight(graph.addEdge(it.upwardStation.name, it.downwardStation.name), it.distance.toDouble())
    }

    val result = DijkstraShortestPath(graph).getPath(departure.name, destination.name).vertexList

    showShortestPathResult(result)
}

fun minimumTime() {
    TODO()
}
