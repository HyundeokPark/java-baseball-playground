import java.util.List;

public class InputValidator {
    private static final int VALID_SIZE = 3;

    public static boolean validateDuplication(List<Integer> input) {
        return input.stream().distinct().count() == input.size();
    }

    public static boolean validateInputSize(List<Integer> input) {
        return input.size() ==VALID_SIZE;
    }
}
