package number_baseball;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        System.out.println("숫자를 입력해주세요: ");
        return scanner.next();
    }


    public boolean askRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        boolean isContinue = false;
        int input = scanner.nextInt();
        if(input ==1) {
            isContinue = true;
        }
        return isContinue;
    }
}