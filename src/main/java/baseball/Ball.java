package baseball;

public class Ball {
    private int position;
    private int number;

    public Ball(int position, int number) {
        this.position = position;
        this.number = number;
    }

    public BallResult compareNumber(int position, int number) {
        if(isMatchPositionAndNumber(position, number)) return BallResult.STRIKE;
        if(isMatchNumber(number)) return BallResult.BALL;
        return BallResult.NOTHING;
    }

    private boolean isMatchNumber(int number) {
        return this.number == number;
    }

    private boolean isMatchPositionAndNumber(int position, int number) {
        return this.position == position && this.number == number;
    }
}
