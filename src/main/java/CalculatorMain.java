import java.util.Scanner;

public class CalculatorMain {
    static Scanner scanner  = new Scanner(System.in);
    public static void main(String[] args) {
        String value = scanner.nextLine();
        String[] values = value.split(" ");
        Calculator calculator = new Calculator(values);
        calculator.calculate();
    }

}
