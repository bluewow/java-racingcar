package step3.domain;

import step3.factory.CarFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarRacingGame {

    private static final ForwardStrategy FORWARD_STRATEGY = new RandomForwardStrategy();

    private final List<Car> cars;
    private final int numberOfAttempts;
    private List<CarRacingRapScore> rapResults = new ArrayList<>();

    public CarRacingGame(String csvNameLineOfCars, int numberOfAttempts) {
        String[] names = splitAndValidateCsvNameLineOfCars(csvNameLineOfCars);
        this.cars = CarFactory.createsCarsByNames(names);
        this.numberOfAttempts = numberOfAttempts;
    }

    private String[] splitAndValidateCsvNameLineOfCars(String csvNameLineOfCars) {
        String[] names = csvNameLineOfCars.split(",");
        Arrays.stream(names)
                .filter(name -> name.length() > 5)
                .findAny()
                .ifPresent(name -> {
                    throw new IllegalArgumentException();
                });
        return names;
    }

    public void start() {
        for (int i = 0; i < numberOfAttempts; i++) {
            List<Car> rapResult = cars.stream()
                    .map(car -> car.move(FORWARD_STRATEGY))
                    .map(Car::clone)
                    .collect(Collectors.toList());
            rapResults.add(new CarRacingRapScore(rapResult));
        }
    }

    public List<CarRacingRapScore> getRapResults() {
        return rapResults;
    }

}