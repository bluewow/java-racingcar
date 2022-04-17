package study.step4.controller;


import study.step4.domain.Car;
import study.step4.domain.Cars;
import study.step4.domain.strategy.RandomMoveStrategy;
import study.step4.service.RacingGame;
import study.step4.view.InputView;
import study.step4.view.ResultView;

import java.util.List;

public class GameController {
    private final InputView inputView;
    private final ResultView resultView;

    public GameController() {
        this(new InputView(), new ResultView());
    }

    public GameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start() {
        RacingGame racingGame = raceGameSetting();

        gameplay(racingGame);
    }

    private void gameplay(RacingGame racingGame) {
        while (racingGame.nextGame()) {
            resultView.printPlay(racingGame.play());
            resultView.plintln();
        }
    }

    private RacingGame raceGameSetting() {
        Cars cars = carSetting();
        int gameCount = gameCountSetting();

        return new RacingGame(cars, gameCount);
    }

    private int gameCountSetting() {
        return inputView.gameCountSetting();
    }

    private Cars carSetting() {
        return new Cars(inputView.carSetting(), new RandomMoveStrategy());
    }
}
