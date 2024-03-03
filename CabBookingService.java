package com.sauravsharma.cabRatingSystemV1;

import java.util.List;
import java.util.Objects;

public class CabBookingService {

    private static final Double BLACK_LISTED_RATING = 1.0;
    private DriverService driverService;
    private PassengerService passengerService;
    private BlackListService blackListService;

    public void findEligibleDrivers(String passengerName) {
        Passenger passenger = passengerService.fetchPassenger(passengerName);
        Double averageRatingOfPassenger = passengerService.getAverageRating(passenger);
        List<Driver> driverList = driverService.fetchListOfDriversAboveTheGivenAvgRating(averageRatingOfPassenger);

        driverList.removeIf(driver -> blackListService.hasPassengerBlackListedDriver(passenger, driver)
            || blackListService.hasDriverBlackListedPassenger(driver, passenger));

        Driver driver = driverService.fetchDriverWithHighestAvgRating(driverList);

        if (Objects.isNull(driver)) {
            driver = driverService.fetchMaxAvgRatingDriver();

        }
        System.out.println("driver name : " + driver.getName() + " driver rating " + driverService.getAverageRating(driver));


    }

    public void registerRide(Ride ride) {
        driverService.createDriver(ride.getDriver().getName(), ride.getDriver());
        driverService.updateRating(ride.getDriver());

        passengerService.createPassenger(ride.getPassenger().getName(), ride.getPassenger());
        passengerService.updateRating(ride.getPassenger());

        if (Objects.equals(ride.getDriver().getRating(), BLACK_LISTED_RATING)) {
            blackListService.blackListDriver(ride.getPassenger(), ride.getDriver());
        }
        if (Objects.equals(ride.getPassenger().getRating(), BLACK_LISTED_RATING)) {
            blackListService.blackListPassenger(ride.getPassenger(), ride.getDriver());
        }
    }
}
