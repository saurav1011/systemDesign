package com.sauravsharma.cabRatingSystemV1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DriverRepository {

    private final HashMap<String, List<Double>> driverAverageRatingWithNumberOfRidesTillNow = new HashMap<>();

    private Driver maxAvgRatingDriver = new Driver();
    private final HashMap<String, List<Double>> driverRatingsTillNow = new HashMap<>();


    private final HashMap<String, Driver> driverSet = new HashMap<>();


    public Driver fetchDriver(String driverName) {
        if (!driverSet.containsKey(driverName)) {
            throw new RuntimeException();// can create custom exception
        } else {
            driverSet.get(driverName);
        }
        return null;
    }

    public void createDriver(String driverName, Driver driver) {
        driverSet.put(driverName, driver);
    }

    public List<Driver> fetchListOfDriversAboveTheGivenAvgRating(Double thresholdRating) {
        List<Driver> drivers = new ArrayList<>();
        for (HashMap.Entry<String, List<Double>> entry : this.driverAverageRatingWithNumberOfRidesTillNow.entrySet()) {
            String driverName = entry.getKey();
            Double averageRating = entry.getValue().get(0);
            if (averageRating > thresholdRating) {
                drivers.add(driverSet.get(driverName));
            }
        }
        return drivers;
    }

    public Driver fetchMaxAvgRatingDriver() {
        return this.maxAvgRatingDriver;
    }


    public Double getAverageRating(Driver driver) {
        if (!driverAverageRatingWithNumberOfRidesTillNow.containsKey(driver.getName()) || driverAverageRatingWithNumberOfRidesTillNow.get(driver.getName())
            .isEmpty()) {
            return 0.0;
        }
        return driverAverageRatingWithNumberOfRidesTillNow.get(driver.getName()).get(0);
    }


    public void updateRating(Driver driver) {
        if (!driverRatingsTillNow.containsKey(driver.getName()) || driverRatingsTillNow.get(driver.getName()).isEmpty()) {
            driverRatingsTillNow.put(driver.getName(), List.of(driver.getRating()));
            driverAverageRatingWithNumberOfRidesTillNow.put(driver.getName(), List.of(driver.getRating(), 1.0));

            if (Objects.isNull(maxAvgRatingDriver.getRating())) {
                maxAvgRatingDriver = driver;
            } else {
                if (getAverageRating(driver) > maxAvgRatingDriver.getRating()) {
                    maxAvgRatingDriver = driver;
                }
            }

        } else {
            driverRatingsTillNow.get(driver.getName()).add(driver.getRating());

            List<Double> averageRatingWithNumberOfRidesTillNow = driverAverageRatingWithNumberOfRidesTillNow.get(driver.getName());
            Double newAverageRating =
                (averageRatingWithNumberOfRidesTillNow.get(0) * averageRatingWithNumberOfRidesTillNow.get(1) +
                    driver.getRating()) / (averageRatingWithNumberOfRidesTillNow.get(1) + 1);
            Double newNumberOfRidesTillNow = averageRatingWithNumberOfRidesTillNow.get(1) + 1;
            driverAverageRatingWithNumberOfRidesTillNow.remove(driver.getName());
            driverAverageRatingWithNumberOfRidesTillNow.put(driver.getName(), List.of(newAverageRating, newNumberOfRidesTillNow));

            if (getAverageRating(driver) > maxAvgRatingDriver.getRating()) {
                maxAvgRatingDriver = driver;
            }
        }
    }
}
