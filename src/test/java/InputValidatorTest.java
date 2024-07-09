import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class InputValidatorTest {
    @ParameterizedTest(name = "사용자의 입력값에 중복을 확인한다.")
    @MethodSource("provideUserInput")
    void validateDuplication(List<Integer> input, boolean result){
        assertThat(InputValidator.validateDuplication(input)).isEqualTo(result);
    }

    @ParameterizedTest(name = "사용자의 입력값에 중복을 확인한다.")
    @MethodSource("provideVariousSizeUserInput")
    void validateInputSize(List<Integer> input, boolean result) {
        assertThat(InputValidator.validateInputSize(input)).isEqualTo(result);
    }

    private static Stream<Arguments> provideVariousSizeUserInput() {
        return Stream.of(
                Arguments.of(List.of(1,2,3), true),
                Arguments.of(List.of(1,2), false),
                Arguments.of(List.of(1,2,4,5), false)
        );
    }

    private static Stream<Arguments> provideUserInput() {
        return Stream.of(
                Arguments.of(List.of(1,2,3), true),
                Arguments.of(List.of(1,2,6), true),
                Arguments.of(List.of(1,2,5), true),
                Arguments.of(List.of(1,2,2), false)
        );
    }

}
