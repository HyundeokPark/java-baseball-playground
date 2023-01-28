import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Calculator {
    private ScriptEngineManager em = new ScriptEngineManager();
    private ScriptEngine engine = em.getEngineByName("JavaScript");

    private final String[] values;
    private Queue<String> expression = new LinkedList<>();
    private static int firstOperand;
    private static int secondOperand;
    public Calculator(String[] values) {
        this.values = values;
        for(String value : values){
            expression.add(value);
        }
    }

    public void calculate() {
        System.out.println("계산결과 출력!");
        while(expression.iterator().hasNext()){
            String value = expression.poll();
            if(value.matches("[0-9]*")){
                firstOperand = Integer.parseInt(value);
            }else{
                secondOperand = Integer.parseInt(expression.poll());
                Operator operator = new Operator(firstOperand,value,secondOperand);
                firstOperand = operator.operate();
            }
        }
        System.out.println(firstOperand);
    }


    class Operator{
        private int firstOperand;
        private int secondOperand;
        private String operateValue;

        public Operator(int firstOperand, String operateValue, int secondOperand) {
            this.firstOperand = firstOperand;
            this.secondOperand = secondOperand;
            this.operateValue = operateValue;
        }

        public int operate() {

            return 0;
        }
    }
}
