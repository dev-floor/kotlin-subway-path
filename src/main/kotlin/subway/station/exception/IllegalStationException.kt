package subway.station.exception

class IllegalStationException : IllegalArgumentException {
    constructor() : super()

    constructor(s: String) : super(s)
}
