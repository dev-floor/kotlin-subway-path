package subway.domain

import org.jgrapht.graph.DefaultWeightedEdge

data class WeightedEdge(var subWeight: Int = 0) : DefaultWeightedEdge()
