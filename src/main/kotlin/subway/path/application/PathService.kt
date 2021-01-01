package subway.path.application

import org.jgrapht.GraphPath
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import subway.common.exception.NOT_EXISTS_PATH
import subway.common.exception.NOT_EXISTS_STATION
import subway.common.exception.SAME_DEPARTURE_DESTINATION_MESSAGE
import subway.path.domain.PathType
import subway.path.domain.SubwayGraphFactory
import subway.path.domain.SubwayWeightedEdge
import subway.section.domain.SectionRepository
import subway.station.domain.Station
import subway.station.domain.StationRepository

class PathService(
    private val stationRepository: StationRepository,
    private val sectionRepository: SectionRepository,
) {
    fun show(request: PathRequest): PathResponse {
        validate(request)

        val pathType = PathType.from(request.pathType)
        val graph = SubwayGraphFactory.create(
            pathType,
            stationRepository.findAll(),
            sectionRepository.findAll()
        )

        return responsePathResult(
            pathType,
            findShortestPath(DijkstraShortestPath(graph), request.departure, request.destination)
        )
    }

    private fun validate(request: PathRequest) {
        require(
            !request.departure.isUpwardEndStation() &&
                stationRepository.existsByName(request.departureName)
        ) { NOT_EXISTS_STATION }
        require(
            !request.destination.isUpwardEndStation() &&
                stationRepository.existsByName(request.destinationName)
        ) { NOT_EXISTS_STATION }
        require(request.departure != request.destination) { SAME_DEPARTURE_DESTINATION_MESSAGE }
    }

    private fun findShortestPath(
        shortestPath: DijkstraShortestPath<Station, SubwayWeightedEdge>,
        departure: Station,
        destination: Station,
    ) = try {
        shortestPath.getPath(departure, destination)
    } catch (e: NullPointerException) {
        throw IllegalArgumentException(NOT_EXISTS_PATH)
    }

    private fun responsePathResult(
        pathType: PathType,
        graphPath: GraphPath<Station, SubwayWeightedEdge>,
    ): PathResponse {
        val vertexList = graphPath.vertexList ?: throw IllegalArgumentException(NOT_EXISTS_PATH)
        val weight = graphPath.weight
        val subWeight = graphPath.edgeList
            .map { it.subWeight }
            .sum()

        return PathResponse.of(pathType, vertexList, weight, subWeight)
    }
}
