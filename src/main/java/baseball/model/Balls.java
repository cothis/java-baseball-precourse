package baseball.model;

import baseball.util.UserInputSeparator;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static baseball.model.BallNumber.MAX_BOUNDARY;
import static baseball.model.BallNumber.MIN_BOUNDARY;

public class Balls {
    public static final int BALL_COUNT = 3;
    private final Map<BallNumber, Integer> ballMap = new HashMap<>();
    private int sequence = 0;

    private Balls() {}

    public Set<BallNumber> getBalls() {
        return ballMap.keySet();
    }

    public Map<BallNumber, Integer> getBallMap() {
        return ballMap;
    }

    public static Balls createByRandoms() {
        checkGenerateEnabled();
        final Balls balls = new Balls();
        while (balls.size() < BALL_COUNT) {
            balls.add(new BallNumber(Randoms.pickNumberInRange(MIN_BOUNDARY, MAX_BOUNDARY)));
        }
        return balls;
    }

    public static Balls createByUserInput(final String userInput) {
        checkGenerateEnabled();
        final Balls balls = new Balls();
        for (String input : UserInputSeparator.separateString(userInput, BALL_COUNT)) {
            balls.add(new BallNumber(input));
        }
        return balls;
    }

    @Override
    public String toString() {
        return "ballMap=" + ballMap;
    }

    private static void checkGenerateEnabled() {
        if (MAX_BOUNDARY - MIN_BOUNDARY + 1 < BALL_COUNT) {
            throw new IllegalStateException("BALL COUNT가 범위보다 큽니다. 생성을 진행할 수 없습니다.");
        }
    }

    private void add(BallNumber ballNumber) {
        this.ballMap.put(ballNumber, sequence++);
    }

    private int size() {
        return this.ballMap.size();
    }
}
