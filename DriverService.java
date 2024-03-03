package com.sauravsharma.cabRatingSystemV1;

import java.util.List;

public class DriverService {

    private DriverRepository driverRepository;


    public void updateRating(Driver driver) {
        driverRepository.updateRating(driver);
    }

    public Double getAverageRating(Driver driver) {
        return driverRepository.getAverageRating(driver);
    }

    public void createDriver(String driverName, Driver driver) {
        driverRepository.createDriver(driverName, driver);
    }

    public Driver fetchDriver(String driverName) {
        return driverRepository.fetchDriver(driverName);
    }

    public List<Driver> fetchListOfDriversAboveTheGivenAvgRating(Double thresholdRating) {
        return driverRepository.fetchListOfDriversAboveTheGivenAvgRating(thresholdRating);
    }

    public Driver fetchMaxAvgRatingDriver() {
        return driverRepository.fetchMaxAvgRatingDriver();
    }

    public Driver fetchDriverWithHighestAvgRating(List<Driver> driverList) {
        if (driverList == null || driverList.isEmpty()) {
            return null; // Return null if the list is empty or null
        }

        Driver driverWithHighestRating = null;
        double highestRating = Double.MIN_VALUE; // Initialize with the minimum possible value

        for (Driver driver : driverList) {
            double averageRating = getAverageRating(driver);
            if (averageRating > highestRating) {
                highestRating = averageRating;
                driverWithHighestRating = driver;
            }
        }

        return driverWithHighestRating;
    }
}
