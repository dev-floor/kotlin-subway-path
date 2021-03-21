package subway.service

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.WeightedMultigraph
import subway.domain.WeightedEdge
import subway.domain.dto.Path
import subway.repository.SectionRepository
import subway.repository.StationRepository

class PathService(
    private val departure: String,
    private val destination: String,
    private val graph: WeightedMultigraph<String, WeightedEdge> =
        WeightedMultigraph<String, WeightedEdge>(WeightedEdge::class.java)
) {
    fun path(select: Int): Path { // 1: Distance, 2: Time
        getGraph(select)

        DijkstraShortestPath(graph).getPath(departure, destination).apply {
            return Path(
                distance = weight.toInt(),
                time = edgeList.map { it.subWeight }.sum(),
                route = vertexList
            )
        }
    }

    private fun getGraph(
        select: Int
    ) {
        val stations = StationRepository.findAll()
        val sections = SectionRepository.findAll()

        stations.map { graph.addVertex(it.name) }
        sections.map {
            setGraphEdge(
                edge = graph.addEdge(it.upwardStation.name, it.downwardStation.name),
                weight = if (select == DISTANCE) it.distance!! else it.time!!,
                subWeight = if (select == DISTANCE) it.time!! else it.distance!!
            )
        }
    }

    private fun setGraphEdge(
        edge: WeightedEdge,
        weight: Int,
        subWeight: Int
    ) {
        edge.subWeight = subWeight
        graph.setEdgeWeight(edge, weight.toDouble())
    }

    companion object {
        const val DISTANCE = 1
    }
}
