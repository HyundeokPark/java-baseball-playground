package string_calculator;

public class InputParser {
    public String[] parse(String input) {
        //입력받은 문자열이 null이거나 공백만 있는 경우, IllegalArgumentException을 발생시킨다.
        if(input == null || input.trim().isEmpty()){
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
        input = input.replaceAll(" ", "");
        //숫자와 사칙연산 외의 문자열이 포함되었는지 정규식으로 검증한다.
        if(input.matches(".*[^0-9\\+\\-\\*\\/].*")){
            throw new IllegalArgumentException("숫자와 연산자 외의 문자가 포함되어 있습니다.");
        }

        // 연산자가 연속으로 되어있는 경우 예외를 발생시킨다.
        if(input.matches(".*[\\+\\-\\*\\/]{2,}.*")){
            throw new IllegalArgumentException("연산자가 연속되어 있습니다.");
        }

        //숫자와 사칙연산만 있는 경우, 숫자와 연산자로 나누어 배열로 반환한다. 이때, 1자리 숫자 뿐 아닌, 두자리, 세자리 숫자도 고려한다.
        return input.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    }
}
