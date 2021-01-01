package subway.ui.common

import subway.ui.main.MainFeature

const val MAIN_SCREEN_TITLE = "\n## 메인 화면"

const val SELECT_FEATURE = "\n### 원하는 기능을 선택하세요."

const val SEPARATE_LINE = "[INFO] ---"

fun generateScreenTitle(feature: MainFeature) =
    "\n## ${feature.category} ${feature.featureType.type} 화면"

fun generateErrorMessage(message: String) = "\n[ERROR] $message"

fun generateInfoMessage(message: String) = "[INFO] $message"

fun generateSuccessMessage(category: String, featureType: FeatureType) =
    "\n[INFO] 지하철 ${category}이 ${featureType.type}되었습니다."
