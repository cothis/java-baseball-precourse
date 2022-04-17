package baseball.view;

import baseball.model.TryResult;
import camp.nextstep.edu.missionutils.Console;

public class GameView {

    public static final int RESTART = 1;
    public static final int EXIT = 2;
    public static final String REQUEST_NUMBER_MSG = "숫자를 입력해주세요 : ";
    public static final String COMPLETE_MSG = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    public static final String RESTART_OR_EXIT_MSG = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    public static final String EXIT_MSG = "게임 종료";
    public static final String MENU_RANGE_EXCEPTION_MSG_HOLDER = "잘못 입력하셨습니다. 입력하신 숫자 : %d";

    private GameView() {}

    public static String requestInsertNumbers() {
        System.out.print(REQUEST_NUMBER_MSG);
        return Console.readLine();
    }

    public static void printResult(final TryResult result) {
        System.out.println(result);
    }

    public static void printCompleteMessage() {
        System.out.println(COMPLETE_MSG);
    }

    public static int requestRestartOrExit() {
        System.out.println(RESTART_OR_EXIT_MSG);
        final int inputMenu = Integer.parseInt(Console.readLine());
        checkRestartMenuIndex(inputMenu);
        return inputMenu;
    }

    public static void printExit() {
        System.out.println(EXIT_MSG);
    }

    private static void checkRestartMenuIndex(int inputMenu) {
        if (inputMenu != RESTART && inputMenu != EXIT) {
            throw new IllegalArgumentException(String.format(MENU_RANGE_EXCEPTION_MSG_HOLDER, inputMenu));
        }
    }
}
