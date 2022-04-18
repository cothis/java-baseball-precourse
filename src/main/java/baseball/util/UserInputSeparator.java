package baseball.util;

import java.util.*;

public class UserInputSeparator {
    public static final String SIZE_EXCEPTION_MSG_HOLDER = "BALL SIZE가 일치하지 않습니다. 입력문자열: %s, 필요 갯수: %d, 현재 갯수: %d";

    private UserInputSeparator() {}

    public static List<String> separateString(final String userInput, final int expectSize) {
        final String[] seperated = userInput.split("");
        validateSize(userInput, seperated.length, expectSize);
        List<String> result = new ArrayList<>();
        for (String s : seperated) {
            if (result.contains(s)) {
                continue;
            }
            result.add(s);
        }
        validateSize(userInput, result.size(), expectSize);
        return result;
    }

    private static void validateSize(String userInput, int size, int expectSize) {
        if (size != expectSize) {
            throw new IllegalArgumentException(String.format(SIZE_EXCEPTION_MSG_HOLDER, userInput, expectSize, size));
        }
    }
}
