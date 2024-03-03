package com.sauravsharma.cabRatingSystemV1;

import java.util.HashMap;
import java.util.HashSet;

public class BlackListRepository {

    private final HashMap<String, HashSet<String>> passengersWhoGaveOneRatingToDriversSet = new HashMap<>();
    private final HashMap<String, HashSet<String>> driversWhoGaveOneRatingToPassengersSet = new HashMap<>();


    public boolean hasPassengerBlackListedDriver(Passenger passenger, Driver driver) {
        return passengersWhoGaveOneRatingToDriversSet.containsKey(passenger.getName()) &&
            passengersWhoGaveOneRatingToDriversSet.get(passenger.getName()).contains(driver.getName());
    }

    public boolean hasDriverBlackListedPassenger(Driver driver, Passenger passenger) {
        return driversWhoGaveOneRatingToPassengersSet.containsKey(driver.getName()) &&
            driversWhoGaveOneRatingToPassengersSet.get(driver.getName()).contains(passenger.getName());
    }

    public void blackListPassenger(Passenger passenger, Driver driver) {
        if (driversWhoGaveOneRatingToPassengersSet.containsKey(driver.getName())) {
            driversWhoGaveOneRatingToPassengersSet.get(driver.getName()).add(passenger.getName());
        } else {
            HashSet<String> blackListedPassengerSet = new HashSet<>();
            blackListedPassengerSet.add(passenger.getName());
            driversWhoGaveOneRatingToPassengersSet.put(driver.getName(), blackListedPassengerSet);
        }
    }

    public void blackListDriver(Passenger passenger, Driver driver) {
        if (passengersWhoGaveOneRatingToDriversSet.containsKey(passenger.getName())) {
            passengersWhoGaveOneRatingToDriversSet.get(passenger.getName()).add(driver.getName());
        } else {
            HashSet<String> blackListedDriverSet = new HashSet<>();
            blackListedDriverSet.add(driver.getName());
            passengersWhoGaveOneRatingToDriversSet.put(passenger.getName(), blackListedDriverSet);
        }
    }
}
