package string_calculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StringCalculator {
    private InputParser inputParser = new InputParser();

    public int calculate(String input) {
        String[] parsed = parse(input);
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.stream(parsed).toList());

        int result = Integer.parseInt(queue.poll());

        while (!queue.isEmpty()) {
            String operator = queue.poll();
            int num2 = Integer.parseInt(queue.poll());
            switch (operator) {
                case "+":
                    result += num2;
                    break;
                case "-":
                    result -= num2;
                    break;
                case "*":
                    result *= num2;
                    break;
                case "/":
                    result /= num2;
                    break;
            }
        }
        return result;
    }
    private String[] parse(String input) {
        return inputParser.parse(input);
    }
}
