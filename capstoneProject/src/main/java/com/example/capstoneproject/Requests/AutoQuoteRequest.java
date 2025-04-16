package com.example.capstoneproject.Requests;

import java.time.LocalDate;

/**
 * Represents a request for creating an AutoQuote.
 * Contains details such as vehicle ID, driver age, accident count, base rate, and discount factor.
 */
public class AutoQuoteRequest {
    private int vehicleId;
    private int driverAge;
    private int accidentCount;
    private double baseRate;
    private double discountFactor;
    private int customerId;
    private boolean paid;
    private LocalDate expiryDate;

    // Getters and setters

    /**
     * Gets the vehicle ID associated with the quote request.
     *
     * @return The vehicle ID.
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID for the quote request.
     *
     * @param vehicleId The vehicle ID.
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the driver's age for the auto quote.
     *
     * @return The driver's age.
     */
    public int getDriverAge() {
        return driverAge;
    }

    /**
     * Sets the driver's age for the auto quote.
     *
     * @param driverAge The driver's age.
     */
    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    /**
     * Gets the accident count associated with the driver.
     *
     * @return The accident count.
     */
    public int getAccidentCount() {
        return accidentCount;
    }

    /**
     * Sets the accident count for the driver.
     *
     * @param accidentCount The accident count.
     */
    public void setAccidentCount(int accidentCount) {
        this.accidentCount = accidentCount;
    }

    /**
     * Gets the base rate for the auto quote.
     *
     * @return The base rate.
     */
    public double getBaseRate() {
        return baseRate;
    }

    /**
     * Sets the base rate for the auto quote.
     *
     * @param baseRate The base rate.
     */
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * Gets the discount factor applied to the auto quote.
     *
     * @return The discount factor.
     */
    public double getDiscountFactor() {
        return discountFactor;
    }

    /**
     * Sets the discount factor for the auto quote.
     *
     * @param discountFactor The discount factor.
     */
    public void setDiscountFactor(double discountFactor) {
        this.discountFactor = discountFactor;
    }

    /**
     * Gets the customer ID associated with the quote request.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for the quote request.
     *
     * @param customerId The customer ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Checks whether the quote has been paid.
     *
     * @return True if paid, false otherwise.
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the payment status of the quote.
     *
     * @param paid The payment status.
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Gets the expiry date of the auto quote.
     *
     * @return The expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date for the auto quote.
     *
     * @param expiryDate The expiry date.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
