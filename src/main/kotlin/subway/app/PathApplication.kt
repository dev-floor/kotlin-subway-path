package subway.app

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.WeightedMultigraph
import subway.domain.WeightedEdge
import subway.repository.SectionRepository
import subway.repository.StationRepository
import subway.view.getDeparture
import subway.view.getDestination

fun shortestPath(): Pair<Pair<Double, Int>, Any> {
    val graph = WeightedMultigraph<String, WeightedEdge>(WeightedEdge::class.java)

    val departure = StationRepository.findByName(getDeparture())
    val destination = StationRepository.findByName(getDestination())

    val stations = StationRepository.findAll()
    val sections = SectionRepository.findAll()

    stations.map { graph.addVertex(it.name) }
    sections.map {
            graph.setEdgeWeight(
            graph.addEdge(it.upwardStation.name, it.downwardStation.name)
                .apply { subWeight = it.time!! },
            it.distance!!.toDouble()
        )
    }

    DijkstraShortestPath(graph).getPath(departure.name, destination.name).let { it ->
        it.vertexList
        return Pair(Pair(it.weight, it.edgeList.map { it.subWeight }.sum()), it.vertexList)
    }
}

fun minimumTime() {
    TODO()
}
