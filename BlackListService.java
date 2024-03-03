package com.sauravsharma.cabRatingSystemV1;

public class BlackListService {

    private BlackListRepository blackListRepository;

    public boolean hasPassengerBlackListedDriver(Passenger passenger, Driver driver) {
        return blackListRepository.hasPassengerBlackListedDriver(passenger, driver);
    }

    public boolean hasDriverBlackListedPassenger(Driver driver, Passenger passenger) {
        return blackListRepository.hasDriverBlackListedPassenger(driver, passenger);
    }

    public void blackListPassenger(Passenger passenger, Driver driver) {
        blackListRepository.blackListPassenger(passenger, driver);
    }

    public void blackListDriver(Passenger passenger, Driver driver) {
        blackListRepository.blackListDriver(passenger, driver);
    }
}
