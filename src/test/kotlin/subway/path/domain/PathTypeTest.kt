package subway.path.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import subway.common.exception.INVALID_PATH_TYPE_MESSAGE

@Suppress("NonAsciiCharacters")
internal class PathTypeTest {
    @Test
    internal fun `of() - 존재하지 않는 경로 조건일 경우 예외 발생`() {
        // given
        val type = "INVALID_TYPE"

        // then
        assertThatIllegalArgumentException().isThrownBy { PathType.of(type) }
            .withMessage(INVALID_PATH_TYPE_MESSAGE)
    }
}
