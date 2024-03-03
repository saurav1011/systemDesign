package com.sauravsharma.cabRatingSystemV1;

public class Ride {

    private Integer rideNumber;
    private Passenger passenger;
    private Driver driver;

    public Ride(Integer rideNumber, Passenger passenger, Driver driver) {
        this.rideNumber = rideNumber;
        this.passenger = passenger;
        this.driver = driver;
    }

    public Integer getRideNumber() {
        return rideNumber;
    }

    public void setRideNumber(Integer rideNumber) {
        this.rideNumber = rideNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
