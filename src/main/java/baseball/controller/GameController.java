package baseball.controller;

import baseball.model.Balls;
import baseball.model.TryResult;
import baseball.view.GameView;

public class GameController {
    public void main() {
        this.start();
        while (GameView.requestRestartOrExit() == GameView.RESTART) {
            this.start();
        }
        GameView.printExit();
    }

    private void start() {
        final Balls answerBalls = Balls.createByRandoms();
        TryResult result = null;
        while(isPlaying(result)) {
            result = new TryResult(answerBalls, this.userBalls());
            GameView.printResult(result);
        }
        GameView.printCompleteMessage();
    }

    private boolean isPlaying(TryResult result) {
        if (result == null) {
            return true;
        }

        return !result.isComplete();
    }

    private Balls userBalls() {
        return Balls.createByUserInput(GameView.requestInsertNumbers());
    }
}
