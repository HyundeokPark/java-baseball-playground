package number_baseball;

import static number_baseball.gameStatus.*;

public class GameController {
    private final NumberBaseball numberBaseball;
    private final InputView inputView;
    private final ResultView resultView;
    private gameStatus status = NOT_STARTED;


    public gameStatus getStatus() {
        return status;
    }

    public GameController(NumberBaseball numberBaseball, InputView inputView, ResultView resultView) {
        this.numberBaseball = numberBaseball;
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void
    startGame() {
        if(status!=NOT_STARTED) throw new IllegalStateException("이미 시작된 게임입니다.");
            status = START;
            String number = numberBaseball.createNumber();
            while(status == START) {
                String inputNumber = inputView.getInput();
                while(true){
                    if(numberBaseball.validateInput(inputNumber)) break;
                    inputNumber = inputView.getInput();
                }
                GameResult result = numberBaseball.compareNumber(number,    inputNumber);
                resultView.printGameResult(result);
                if(result.getStrike() ==3){
                    resultView.printGameEnd();
                    status = END;
                    if(inputView.askRestart()){
                        restartGame();
                    }else{
                        quitGame();
                    }
                }
            }

    }

    public void quitGame() {
        if (status != END) throw new IllegalStateException("게임이 종료되지 않았습니다.");
        status = QUIT;
        System.exit(0);
    }

    public void restartGame() {
        if(status != END) throw new IllegalStateException("게임이 종료되지 않았습니다.");
        status = NOT_STARTED;
        startGame();
    }

    public void endGame() {
        if(status!=START) throw new IllegalStateException("게임이 시작되지 않았습니다.");
        status = END;
    }
}
