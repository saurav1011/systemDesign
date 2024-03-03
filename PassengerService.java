package com.sauravsharma.cabRatingSystemV1;

public class PassengerService {

    private PassengerRepository passengerRepository;

    public void updateRating(Passenger passenger) {
        passengerRepository.updateRating(passenger);
    }

    public Double getAverageRating(Passenger passenger) {
        return passengerRepository.getAverageRating(passenger);
    }

    public void createPassenger(String passengerName, Passenger passenger) {
        passengerRepository.createPassenger(passengerName, passenger);
    }

    public Passenger fetchPassenger(String passengerName) {
        return passengerRepository.fetchPassenger(passengerName);
    }

}
