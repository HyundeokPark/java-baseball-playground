package string_calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

/**
 * 요구사항
 * 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
 * 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
 * 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
 */
class StringCalculatorTest {

    @ParameterizedTest
    @CsvSource(value = {"2+3:5","2 + 3 + 4:9","2 + 3 + 4 + 5:14"},delimiter = ':')
    @DisplayName("덧셈을 수행한다.")
    void plus(String input, int expected){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.calculate(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"2-3:-1","2 - 3 - 4:-5","2 - 3 - 4 - 5:-10"},delimiter = ':')
    @DisplayName("뺄셈을 수행한다.")
    void minus(String input, int expected){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.calculate(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"2*3:6","2 * 3 * 4:24","2 * 3 * 4 * 5:120"},delimiter = ':')
    @DisplayName("곱셈을 수행한다.")
    void multiply(String input, int expected){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.calculate(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"2/3:0","2 / 3 / 4:0","2 / 3 / 4 / 5:0"},delimiter = ':')
    @DisplayName("나눗셈을 수행한다.")
    void divide(String input, int expected){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.calculate(input)).isEqualTo(expected);
    }

    //나눗셈을 수행할 때 0으로 나누는 경우에 대한 여러개의 테스트케이스를 파라미터라이즈드 테스트로 추가한다.
    @ParameterizedTest
    @ValueSource(strings = {"2/0","2 / 0 / 4","2 / 0 / 4 / 5"})
    @DisplayName("0으로 나누는 경우에 에러를 발생한다.")
    void divideByZero(String input){
        StringCalculator stringCalculator = new StringCalculator();
        assertThatThrownBy(() -> {
            stringCalculator.calculate(input);
        }).isInstanceOf(ArithmeticException.class);
    }
}