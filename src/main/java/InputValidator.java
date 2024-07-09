import java.util.List;

public class InputValidator {
    public static boolean validateDuplication(List<Integer> input) {
        return input.stream().distinct().count() == input.size();
    }
}
