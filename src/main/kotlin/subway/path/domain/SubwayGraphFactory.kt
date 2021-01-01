package subway.path.domain

import org.jgrapht.graph.WeightedMultigraph
import subway.section.domain.Section
import subway.station.domain.Station

object SubwayGraphFactory {
    fun create(
        pathType: PathType,
        stations: List<Station>,
        sections: List<Section>,
    ): WeightedMultigraph<Station, SubwayWeightedEdge> {
        val graph = WeightedMultigraph<Station, SubwayWeightedEdge>(SubwayWeightedEdge::class.java)

        stations.forEach { graph.addVertex(it) }
        sections.forEach {
            if (it.preStation == Station.UPWARD_END_STATION) return@forEach
            val edge = graph.addEdge(it.preStation, it.station)
                .apply { subWeight = pathType.subweight(it) }
            graph.setEdgeWeight(edge, pathType.weight(it))
        }

        return graph
    }
}
