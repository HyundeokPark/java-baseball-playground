package number_baseball;

import java.util.Objects;

public class GameResult {
    private int strike;
    private int ball;
    private boolean nothing;

    public GameResult(int strike, int ball, boolean nothing) {
        this.strike = strike;
        this.ball = ball;
        this.nothing = nothing;
    }

    @Override
    public String toString() {
        if (nothing) {
            return "낫싱";
        } else if (strike > 0 && ball > 0) {
            return strike + "스트라이크 " + ball + "볼";
        } else if (strike > 0) {
            return strike + "스트라이크";
        } else {
            return ball + "볼";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return strike == that.strike && ball == that.ball && nothing == that.nothing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strike, ball, nothing);
    }

    public int getStrike() {
        return strike;
    }
}
