package subway.common.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
internal class NameTest {
    @ParameterizedTest
    @ValueSource(strings = ["역", ""])
    internal fun `constructor_이름이 2글자 미만이면 예외 발생`(name: String) {
        // then
        assertThatIllegalArgumentException().isThrownBy { Name(name) }
    }
}
