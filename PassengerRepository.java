package com.sauravsharma.cabRatingSystemV1;

import java.util.HashMap;
import java.util.List;

public class PassengerRepository {

    private final HashMap<String, List<Double>> passengerAverageRatingWithNumberOfRidesTillNow = new HashMap<>();
    private final HashMap<String, List<Double>> passengerRatingsTillNow = new HashMap<>();

    private final HashMap<String, Passenger> passengerSet = new HashMap<>();


    public Double getAverageRating(Passenger passenger) {
        if (passengerAverageRatingWithNumberOfRidesTillNow.get(passenger.getName()).isEmpty()) {
            return 0.0;
        }
        return passengerAverageRatingWithNumberOfRidesTillNow.get(passenger.getName()).get(0);
    }

    public Passenger fetchPassenger(String passengerName) {
        if (!passengerSet.containsKey(passengerName)) {
            throw new RuntimeException();// can create custom exception
        } else {
            passengerSet.get(passengerName);
        }
        return null;
    }

    public void createPassenger(String passengerName, Passenger passenger) {
        passengerSet.put(passengerName, passenger);
    }

    public void updateRating(Passenger passenger) {
        if (!passengerRatingsTillNow.containsKey(passenger.getName()) || passengerRatingsTillNow.get(passenger.getName()).isEmpty()) {
            passengerRatingsTillNow.put(passenger.getName(), List.of(passenger.getRating()));
            passengerAverageRatingWithNumberOfRidesTillNow.put(passenger.getName(), List.of(passenger.getRating(), 1.0));
        } else {
            passengerRatingsTillNow.get(passenger.getName()).add(passenger.getRating());
            List<Double> averageRatingWithNumberOfRidesTillNow = passengerAverageRatingWithNumberOfRidesTillNow.get(passenger.getName());
            Double newAverageRating =
                (averageRatingWithNumberOfRidesTillNow.get(0) * averageRatingWithNumberOfRidesTillNow.get(1) +
                    passenger.getRating()) / (averageRatingWithNumberOfRidesTillNow.get(1) + 1);
            Double newNumberOfRidesTillNow = averageRatingWithNumberOfRidesTillNow.get(1) + 1;
            passengerAverageRatingWithNumberOfRidesTillNow.remove(passenger.getName());
            passengerAverageRatingWithNumberOfRidesTillNow.put(passenger.getName(), List.of(newAverageRating, newNumberOfRidesTillNow));
        }
    }
}
