package baseball.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserInputSeparatorTest {

    @Test
    @DisplayName("입력을 Set<String> 으로 분리")
    void separate() {
        // given
        final String userInput = "123";

        // when
        final List<String> inputs = UserInputSeparator.separateString(userInput, 3);

        // then
        assertThat(inputs.size()).isSameAs(3);
    }

    @ParameterizedTest
    @CsvSource(value = {"111:1", "1234:4"}, delimiter = ':')
    @DisplayName("예외 발생")
    void mismatchSize(final String userInput, final int size) {
        // when
        // then
        assertThatThrownBy(() -> UserInputSeparator.separateString(userInput, 3))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(UserInputSeparator.SIZE_EXCEPTION_MSG_HOLDER, userInput, 3, size);
    }

    @Test
    @DisplayName("중복 입력 발생 시")
    void duplicated() {
        // given
        final String userInput = "111";

        // when
        // then
        assertThatThrownBy(() -> UserInputSeparator.separateString(userInput, 3))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(UserInputSeparator.SIZE_EXCEPTION_MSG_HOLDER, userInput, 3, 1);
    }
}