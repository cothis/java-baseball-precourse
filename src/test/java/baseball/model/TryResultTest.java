package baseball.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static baseball.model.TryResult.*;
import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class TryResultTest {

    @ParameterizedTest
    @CsvSource(
            value = {
                    "123:123:3:" + STRIKE_MSG,
                    "789:789:3:" + STRIKE_MSG,
                    "123:231:3:" + BALL_MSG,
                    "789:978:3:" + BALL_MSG
            },
            delimiter = ':')
    @DisplayName("3스트라이크 또는 3볼")
    void strikeAll(String answer, String userInput, int strike, String message) {
        final List<Integer> randomNumbers = parseMockRandomNumbers(answer);
        assertRandomNumberInRangeTest(
                () -> {
                    final TryResult tryResult = getTryResult(userInput);

                    // then
                    assertThat(tryResult).hasToString(strike + message);
                }, randomNumbers.get(0), randomNumbers.subList(1, randomNumbers.size()).toArray(new Integer[0]));
    }

    @ParameterizedTest
    @CsvSource(value = {"123:456", "789:456"}, delimiter = ':')
    @DisplayName("낫싱")
    void ballAll(String answer, String userInput) {
        final List<Integer> randomNumbers = parseMockRandomNumbers(answer);
        assertRandomNumberInRangeTest(
                () -> {
                    final TryResult tryResult = getTryResult(userInput);

                    // then
                    assertThat(tryResult).hasToString(NOTHING_MSG);
                }, randomNumbers.get(0), randomNumbers.subList(1, randomNumbers.size()).toArray(new Integer[0]));
    }

    @ParameterizedTest
    @CsvSource(value = {"123:132:2:1", "123:134:1:1", "123:213:2:1"}, delimiter = ':')
    @DisplayName("볼 and  스트라이크")
    void ballAndStrike(String answer, String userInput, int ballCount, int strikeCount) {
        final List<Integer> randomNumbers = parseMockRandomNumbers(answer);
        assertRandomNumberInRangeTest(
                () -> {
                    final TryResult tryResult = getTryResult(userInput);

                    // then
                    assertThat(tryResult).hasToString(ballCount + BALL_MSG + " " + strikeCount + STRIKE_MSG);
                }, randomNumbers.get(0), randomNumbers.subList(1, randomNumbers.size()).toArray(new Integer[0]));
    }

    private TryResult getTryResult(String userInput) {
        final Balls answerBalls = Balls.createByRandoms();
        final Balls userBalls = Balls.createByUserInput(userInput);

        // when
        return new TryResult(answerBalls, userBalls);
    }

    private List<Integer> parseMockRandomNumbers(String answer) {
        final String[] randoms = answer.split("");
        final List<Integer> randomNumbers = new ArrayList<>();
        for (String random : randoms) {
            randomNumbers.add(Integer.valueOf(random));
        }
        return randomNumbers;
    }

}