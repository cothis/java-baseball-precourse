package baseball.model;

import java.util.Objects;

public class BallNumber {
    public static final String BOUNDARY_EXCEPTION_MSG_HOLDER = "숫자 입력 범위 초과입니다. %d ~ %d 사이의 값을 입력해주세요.";
    public static final int MIN_BOUNDARY = 1;
    public static final int MAX_BOUNDARY = 9;
    private final int number;

    public BallNumber(final String input) {
        this.number = convertToInt(input);
    }

    public BallNumber(final int number) {
        checkRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BallNumber that = (BallNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    private int convertToInt(final String input) {
        try {
            final int userNumber = Integer.parseInt(input);
            checkRange(userNumber);
            return userNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void checkRange(final int number) {
        if (number < MIN_BOUNDARY || number > MAX_BOUNDARY) {
            throw new IllegalArgumentException(String.format(BOUNDARY_EXCEPTION_MSG_HOLDER, MIN_BOUNDARY, MAX_BOUNDARY));
        }
    }
}
