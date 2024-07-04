package number_baseball;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController(new NumberBaseball(), new InputView(), new ResultView());
        gameController.startGame();
    }
}
