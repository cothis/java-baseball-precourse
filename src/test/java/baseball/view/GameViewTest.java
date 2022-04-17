package baseball.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameViewTest extends NsTest {

    @Test
    @DisplayName("재시작 또는 종료에 없는 숫자를 입력 시")
    void requestRestartOrExitWithOutOfRange() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0"))
                        .isExactlyInstanceOf(IllegalArgumentException.class)
                        .hasMessage(GameView.MENU_RANGE_EXCEPTION_MSG_HOLDER, 0)
        );
    }

    @Test
    @DisplayName("재시작 또는 종료에 문자를 입력 시")
    void requestRestartOrExitWithNumberFormatException() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("not a number"))
                        .isExactlyInstanceOf(NumberFormatException.class)
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    protected void runMain() {
        GameView.requestRestartOrExit();
    }
}