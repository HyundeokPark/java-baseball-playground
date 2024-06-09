package number_baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * 기능 요구 사항
 * 기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.
 *
 * 같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 포볼 또는 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방(컴퓨터)의 수를 맞추면 승리한다.
 * 예시: 상대방(컴퓨터)의 수가 425일 때
 * 123을 제시한 경우 : 1스트라이크
 * 456을 제시한 경우 : 1볼 1스트라이크
 * 789를 제시한 경우 : 낫싱
 * 위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다. 게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
 * 이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
 * 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
 *
 *
 *
 *  * 프로그래밍 요구사항
 *  * 자바 코드 컨벤션을 지키면서 프로그래밍한다.
 *  * 기본적으로 Google Java Style Guide을 원칙으로 한다.
 *  * 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
 *  * indent(인덴트, 들여쓰기) depth를 2가 넘지 않도록 구현한다. 1까지만 허용한다.
 *  * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
 *  * 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
 *  * else 예약어를 쓰지 않는다.
 *  * 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
 *  * else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
 *  * 모든 로직에 단위 테스트를 구현한다. 단, UI(System.out, System.in) 로직은 제외한다.
 *  * 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
 *  * UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
 *  * 3항 연산자를 쓰지 않는다.
 *  * 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
 */
class NumberBaseballTest {

    //1부터 9까지 서로 다른 수로 이루어진 3자리의 문자열을 생성한다.
    @Test
    @DisplayName("1부터 9까지 서로 다른 수로 이루어진 3자리의 문자열을 생성한다.")
    void createNumber() {
        NumberBaseball numberBaseball = new NumberBaseball();
        String number = numberBaseball.createNumber();

        // 생성된 문자열이 3자리인지 확인
        assertEquals(3, number.length());

        // 각 자리의 숫자가 모두 다른지 확인
        assertTrue(number.charAt(0) != number.charAt(1));
        assertTrue(number.charAt(0) != number.charAt(2));
        assertTrue(number.charAt(1) != number.charAt(2));
    }

    // 3자리 숫자만 입력이 가능한지 여러가지 엣지 케이스에 대해서 파라미터라이즈드 테스트를 작성한다.
    @Test
    @DisplayName("3자리 숫자만 입력이 가능하다.")
    void validateInput() {
        NumberBaseball numberBaseball = new NumberBaseball();
        String number = "123";
        assertTrue(numberBaseball.validateInput(number));
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForCompareNumber")
    @DisplayName("스트라이크, 볼, 낫싱을 판단한다.")
    void compareNumber(String number, String input, GameResult expected) {
        NumberBaseball numberBaseball = new NumberBaseball();
        assertEquals(expected, numberBaseball.compareNumber(number, input));
    }

    private static Stream<Arguments> provideTestCasesForCompareNumber() {
        return Stream.of(
                Arguments.of("123", "123", new GameResult(3, 0, false)),
                Arguments.of("123", "312", new GameResult(0, 3, false)),
                Arguments.of("123", "456", new GameResult(0, 0, true)),
                Arguments.of("123", "132", new GameResult(1, 2, false)),
                Arguments.of("123", "321", new GameResult(1, 2, false)),
                Arguments.of("123", "231", new GameResult(0, 3, false)),
                Arguments.of("123", "213", new GameResult(1, 2, false)),
                Arguments.of("123", "124", new GameResult(2, 0, false)),
                Arguments.of("123", "134", new GameResult(1, 1, false)),
                Arguments.of("123", "234", new GameResult(0, 2, false))
        );
    }
}