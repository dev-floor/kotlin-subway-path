package subway.view

const val SEPARATOR_LINE_WITH_STATION = "---"
const val KILOMETER = "km"
const val MINUTE = "분"
const val STATION = "선"
const val SEPARATOR_DISTANCE_AND_TIME = " / "

fun routeMap(routeMap: List<List<Any>>) {
    print("\n## 지하철 노선도")
    var isDistance = false
    var needsInfo = true
    routeMap
        .forEach { route ->
            route.map {
                if (needsInfo)
                    print(INFO_MESSAGE)

                print(it)

                if (it is String && it.endsWith(STATION))
                    print("$INFO_MESSAGE$SEPARATOR_LINE_WITH_STATION")

                if (it is Int) {
                    if (!isDistance) {
                        isDistance = true
                        print("$KILOMETER$SEPARATOR_DISTANCE_AND_TIME")
                        needsInfo = false
                    } else { // if(isTime)
                        isDistance = false
                        print(MINUTE)
                        needsInfo = true
                    }
                }
            }
            println()
        }
}
