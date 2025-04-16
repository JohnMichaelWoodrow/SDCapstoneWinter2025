package com.example.capstoneproject.Quotes;

import jakarta.persistence.*;
import com.example.capstoneproject.Objects.Vehicle;
import java.time.LocalDate;

/**
 * Represents an AutoQuote entity used for calculating car insurance premiums.
 */
@Entity
public class AutoQuote extends Quote {
    private int driverAge;
    private int accidentCount;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    /**
     * Default constructor for AutoQuote.
     */
    public AutoQuote() {}

    /**
     * Constructs a new AutoQuote instance with specified details.
     *
     * @param id            The unique identifier for the quote.
     * @param vehicle       The vehicle associated with the quote.
     * @param driverAge     The age of the driver.
     * @param accidentCount The number of accidents the driver has had.
     * @param premium       The pre-calculated premium for the quote.
     */
    public AutoQuote(int id, Vehicle vehicle, int driverAge, int accidentCount, double premium) {
        super(id, "Auto", premium);
        this.vehicle = vehicle;
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
    }

    /**
     * Calculates the insurance premium based on various risk factors.
     *
     * @param baseRate      The base rate for the auto insurance.
     * @param driverAge     The age of the driver.
     * @param accidentCount The number of accidents the driver has had.
     * @param vehicle       The vehicle associated with the quote.
     * @return The calculated premium.
     */
    public static double calculatePremium(double baseRate, int driverAge, int accidentCount, Vehicle vehicle) {
        double driverFactor = driverAge < 25 ? 2.0 : 1.0;
        double accidentFactor = accidentCount > 2 ? 2.5 : (accidentCount == 1 ? 1.25 : 1.0);
        double vehicleFactor = vehicle.getYear() < LocalDate.now().getYear() - 10 ? 2.0 :
                (vehicle.getYear() < LocalDate.now().getYear() - 5 ? 1.5 : 1.0);
        // Multiply by the tax factor of 1.15 and round the result
        return (double) Math.round(baseRate * driverFactor * accidentFactor * vehicleFactor * 1.15);
    }

    /**
     * Overloaded method for calculating premium with a discount factor applied.
     *
     * @param baseRate       The base rate for the auto insurance.
     * @param driverAge      The age of the driver.
     * @param accidentCount  The number of accidents the driver has had.
     * @param vehicle        The vehicle associated with the quote.
     * @param discountFactor The discount factor applied to the base premium.
     * @return The calculated premium after applying the discount.
     */
    public static double calculatePremium(double baseRate, int driverAge, int accidentCount, Vehicle vehicle, double discountFactor) {
        double basePremium = calculatePremium(baseRate, driverAge, accidentCount, vehicle);
        return (double) Math.round(basePremium * discountFactor);
    }

    // Getters and setters

    /**
     * Gets the age of the driver.
     *
     * @return The driver's age.
     */
    public int getDriverAge() {
        return driverAge;
    }

    /**
     * Sets the age of the driver.
     *
     * @param driverAge The driver's age.
     */
    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    /**
     * Gets the number of accidents the driver has had.
     *
     * @return The accident count.
     */
    public int getAccidentCount() {
        return accidentCount;
    }

    /**
     * Sets the number of accidents the driver has had.
     *
     * @param accidentCount The accident count.
     */
    public void setAccidentCount(int accidentCount) {
        this.accidentCount = accidentCount;
    }

    /**
     * Gets the vehicle associated with the quote.
     *
     * @return The vehicle object.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle associated with the quote.
     *
     * @param vehicle The vehicle object.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
