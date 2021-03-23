package subway.service

import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.WeightedMultigraph
import subway.domain.WeightedEdge
import subway.dto.PathRequest
import subway.dto.PathResponse
import subway.repository.SectionRepository
import subway.repository.StationRepository

object PathService {
    private const val DISTANCE = 1

    private val graph: WeightedMultigraph<String, WeightedEdge> =
        WeightedMultigraph<String, WeightedEdge>(WeightedEdge::class.java)

    fun path(
        pathRequest: PathRequest,
        select: Int
    ): PathResponse { // 1: Distance, 2: Time
        getGraph(select)

        DijkstraShortestPath(graph).getPath(
            pathRequest.departure,
            pathRequest.destination
        ).apply {
            return PathResponse(
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
}
