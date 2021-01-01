package subway.ui.common

enum class FeatureType(val type: String) {
    MANAGE("관리"),
    PRINT("출력"),
    REGISTER("등록"),
    REMOVE("삭제"),
    SHOW("조회"),
    DISTANCE("최단 거리"),
    DURATION("최소 시간"),
    BACK("돌아가기"),
    QUIT("종료");
}
