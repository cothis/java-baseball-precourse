package baseball.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static baseball.model.BallNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BallNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {MIN_BOUNDARY + "", MAX_BOUNDARY + ""})
    @DisplayName("정상 생성(유저 입력)")
    void normalCase(final String input) {
        // given
        final int expect = Integer.parseInt(input);

        // when
        final BallNumber ball = new BallNumber(input);

        // then
        assertThat(ball.getNumber()).isSameAs(expect);
    }

    @ParameterizedTest
    @ValueSource(ints = {MIN_BOUNDARY, MAX_BOUNDARY})
    @DisplayName("정상 생성(숫자)")
    void normalCase(final int input) {
        // when
        final BallNumber ball = new BallNumber(input);

        // then
        assertThat(ball.getNumber()).isSameAs(input);
    }


    @Test
    @DisplayName("입력 잘못 시, IllegalArgumentException 발생")
    void exceptionInput() {
        // given
        final String input = "not a number";

        // when
        // then
        assertThatThrownBy(() -> new BallNumber(input))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {(MIN_BOUNDARY - 1) + "", (MAX_BOUNDARY + 1) + ""})
    @DisplayName("범위 초과 시, IllegalArgumentException 발생")
    void exceptionRangeOver(final String input) {
        // when
        // then
        assertThatThrownBy(() -> new BallNumber(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(BOUNDARY_EXCEPTION_MSG_HOLDER, MIN_BOUNDARY, MAX_BOUNDARY);
    }
}