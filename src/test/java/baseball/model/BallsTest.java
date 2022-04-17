package baseball.model;

import baseball.util.UserInputSeparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BallsTest {

    private final BallNumber[] normalResult = {new BallNumber(1), new BallNumber(2), new BallNumber(3)};

    @Test
    @DisplayName("정상 생성(랜덤)")
    void createNormalByRandom() {
        assertRandomNumberInRangeTest(() -> assertThat(Balls.createByRandoms().getBalls())
                .containsExactly(normalResult), 1, 2, 3);
    }

    @Test
    @DisplayName("정상 생성(랜덤) - 중복 숫자 발생 시")
    void createNormalByRandomDuplicate() {
        assertRandomNumberInRangeTest(() -> assertThat(Balls.createByRandoms().getBalls())
                .containsExactly(normalResult), 1, 2, 2, 2, 3);
    }

    @Test
    @DisplayName("정상 생성(유저입력)")
    void createNormalByUserInput() {
        // given
        final String userInput = "123";

        // when
        final Balls balls = Balls.createByUserInput(userInput);

        // then
        assertThat(balls.getBalls())
                .containsExactly(normalResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"111:1", "112:2"}, delimiter = ':')
    @DisplayName("오류 발생(유저 입력) - 중복")
    void exceptionWithDuplicatedUserInput(final String userInput, final int size) {
        // when
        // then
        assertThatThrownBy(() -> Balls.createByUserInput(userInput))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(UserInputSeparator.SIZE_EXCEPTION_MSG_HOLDER, userInput, Balls.BALL_COUNT, size);
    }
}