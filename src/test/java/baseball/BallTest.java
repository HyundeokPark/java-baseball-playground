package baseball;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BallTest {

    @Test
    void isStrike() {
        Ball ball = new Ball(1,2);
        assertThat(ball.compareNumber(1,2)).isEqualTo(BallResult.STRIKE);
        }

        @Test
        void isBall() {
            Ball ball = new Ball(1,2);
            assertThat(ball.compareNumber(2,2)).isEqualTo(BallResult.BALL);
        }

        @Test
        void isNothing() {
            Ball ball = new Ball(1,2);
            assertThat(ball.compareNumber(3,3)).isEqualTo(BallResult.NOTHING);
        }

}
