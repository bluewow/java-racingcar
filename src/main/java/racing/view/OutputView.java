package racing.view;

import racing.model.Position;
import racing.model.RacingResult;
import racing.model.RoundResult;

import java.util.stream.IntStream;

/*
 *
 * OutputView
 *
 * @version 1.0.0
 *
 * 2021-11-12
 *
 * Copyright tnals1422
 */
public class OutputView {

    private static final String POSITION_SYMBOL = "-";
    private final RacingResult racingResult;

    public OutputView(RacingResult racingResult) {
        this.racingResult = racingResult;
    }

    public void printRacingResult() {
        System.out.println("실행 결과");
        racingResult.records().forEach(this::printRoundResult);
        System.out.println(racingResult.winner() + "가 최종 우승했습니다.");
    }

    private void printRoundResult(RoundResult roundResult) {
        roundResult.records().forEach(car -> {
            System.out.println(car.stringName() + " : " + convertToSymbol(car.getPosition()));
        });
        System.out.println();
    }

    private String convertToSymbol(Position position) {
        String result = "";
        if (position.getValue() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            IntStream.range(0, position.getValue())
                    .forEach(j -> stringBuilder.append(POSITION_SYMBOL));
            result = stringBuilder.toString();
        }
        return result;
    }
}