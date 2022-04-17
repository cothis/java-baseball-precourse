package baseball.model;

import java.util.Map;
import java.util.function.BiPredicate;

public class TryResult {
    public static final String NOTHING_MSG = "낫싱";
    public static final String BALL_MSG = "볼";
    public static final String STRIKE_MSG = "스트라이크";
    private final int ball;
    private final int strike;

    public TryResult(final Balls answer, final Balls userBalls) {
        final Map<BallNumber, Integer> answerBallMap = answer.getBallMap();
        final Map<BallNumber, Integer> userBallMap = userBalls.getBallMap();

        this.strike = calculateCount(answerBallMap, userBallMap, Integer::equals);
        this.ball = calculateCount(answerBallMap, userBallMap, (a, b) -> !a.equals(b));
    }

    private int calculateCount(
            final Map<BallNumber, Integer> answerBallMap,
            final Map<BallNumber, Integer> userBallMap,
            final BiPredicate<Integer, Integer> predicate
    ) {
        int count = 0;
        for (Map.Entry<BallNumber, Integer> answerBallMapEntry : answerBallMap.entrySet()) {
            final Integer userBallPosition = userBallMap.get(answerBallMapEntry.getKey());
            if (userBallPosition == null) {
                continue;
            }
            if (predicate.test(answerBallMapEntry.getValue(), userBallPosition)) {
                count ++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        if (nothing()) {
            return NOTHING_MSG;
        }

        if (onlyBall()) {
            return ball + BALL_MSG;
        }

        if (onlyStrike()) {
            return strike + STRIKE_MSG;
        }

        return ball + BALL_MSG + " " + strike + STRIKE_MSG;
    }

    private boolean nothing() {
        return ball == 0 && strike == 0;
    }

    private boolean onlyBall() {
        return ball != 0 && strike == 0;
    }

    private boolean onlyStrike() {
        return ball == 0 && strike != 0;
    }

    public boolean isComplete() {
        return strike == Balls.BALL_COUNT;
    }
}
