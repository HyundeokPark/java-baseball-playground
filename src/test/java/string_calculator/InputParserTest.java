package string_calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    @DisplayName("입력받은 문자열이 null이거나 빈 문자열이면,오류를 발생한다.")
    void parseNullOrEmptyInput(String invalidInput){
        InputParser ip = new InputParser();
        assertThatThrownBy(() -> {
            ip.parse(invalidInput);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값이 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2+3()", "2-3()", "@@@", "!!!2/3"})
    @DisplayName("입력받은 문자열에 숫자와 연산자 외의 문자가 들어오면 오류를 발생한다.")
    void parseInvalidInput(String invalidInput){
        InputParser ip = new InputParser();
        assertThatThrownBy(() -> {
            ip.parse(invalidInput);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자와 연산자 외의 문자가 포함되어 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2+3", "2-3","2*3", "2/3"})
    @DisplayName("입력받은 문자열에 숫자와 연산자만 있으면, 오류를 발생하지 않는다.")
    void parseValidInput(String validInput){
        InputParser ip = new InputParser();
        assertThatNoException().isThrownBy(()-> {
            ip.parse(validInput);
        });
    }

    @ParameterizedTest
    @MethodSource("parseBlankInputAndExpectation")
    @DisplayName("입력받은 문자열에 공백은 제거한다.")
    void parseBlankIncludedInput(String blankInput, String[] expected){
        InputParser ip = new InputParser();
        assertThatNoException().isThrownBy(()-> {
            String[] parseResult = ip.parse(blankInput);
            assertThat(parseResult).containsExactly(expected);
        });
    }

    @ParameterizedTest
    @MethodSource("parseInputAndExpectation")
    @DisplayName("입력받은 문자열을 숫자와 연사자로 나눈다.")
    void parseNumberAndOperand(String input, String[] expected){
        InputParser ip = new InputParser();
        String[] parseResult = ip.parse(input);
        assertThat(parseResult).containsExactly(expected);
    }

    //문자열에서 연산자가 연속되는 경우에 대한 테스트케이스를 추가한다.
    @ParameterizedTest
    @ValueSource(strings = {"2++3","2 + +3","2 + 3 + +4","2 + 3 + 4 + +5"})
    @DisplayName("연산자가 연속되는 경우에 에러를 발생한다.")
    void continuousOperator(String input){
        InputParser ip = new InputParser();
        assertThatThrownBy(() -> {
            ip.parse(input);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("연산자가 연속되어 있습니다.");
    }

    static Stream<Arguments> parseInputAndExpectation() {
        return Stream.of(
                Arguments.of("2+3", new String[]{"2", "+", "3"}),
                Arguments.of("2-3", new String[]{"2", "-", "3"}),
                Arguments.of("2*3", new String[]{"2", "*", "3"}),
                Arguments.of("2/3", new String[]{"2", "/", "3"}),
                Arguments.of("200/3", new String[]{"200", "/", "3"})
        );
    }

    static Stream<Arguments> parseBlankInputAndExpectation() {
        return Stream.of(
                Arguments.of("2 + 3", new String[]{"2", "+", "3"}),
                Arguments.of("   2 -3", new String[]{"2", "-", "3"}),
                Arguments.of("2*   3", new String[]{"2", "*", "3"}),
                Arguments.of("2 / 3", new String[]{"2", "/", "3"}),
                Arguments.of("200  /     3", new String[]{"200", "/", "3"})
        );
    }
}