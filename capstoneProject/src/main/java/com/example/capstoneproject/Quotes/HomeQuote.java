package com.example.capstoneproject.Quotes;

import jakarta.persistence.*;
import com.example.capstoneproject.Objects.Home;

/**
 * Represents a HomeQuote entity used for calculating home insurance premiums.
 */
@Entity
public class HomeQuote extends Quote {
    @OneToOne
    @JoinColumn(name = "home_id")
    private Home home;

    private double baseRate;  // Base rate for calculating the premium
    private double riskFactor;  // Risk factor influencing the premium

    /**
     * Default constructor for HomeQuote.
     */
    public HomeQuote() {}

    /**
     * Constructs a new HomeQuote instance with specified details.
     *
     * @param id         The unique identifier for the quote.
     * @param home       The home associated with the quote.
     * @param baseRate   The base rate for calculating the premium.
     * @param riskFactor The risk factor influencing the premium.
     */
    public HomeQuote(int id, Home home, double baseRate, double riskFactor) {
        // Calculate premium based on the base rate and risk factor
        super(id, "Home", calculatePremium(baseRate, riskFactor));
        this.home = home;
        this.baseRate = baseRate;
        this.riskFactor = riskFactor;
    }

    /**
     * Calculates the home insurance premium based on the base rate and risk factor.
     *
     * @param baseRate   The base rate used for premium calculation.
     * @param riskFactor The risk factor influencing the premium.
     * @return The calculated premium.
     */
    public static double calculatePremium(double baseRate, double riskFactor) {
        // Multiply baseRate, riskFactor, and add tax (1.15 factor) then round the result
        return (double) Math.round(baseRate * riskFactor * 1.15);
    }

    // Getters and setters

    /**
     * Gets the base rate for premium calculation.
     *
     * @return The base rate.
     */
    public double getBaseRate() {
        return baseRate;
    }

    /**
     * Sets the base rate and updates the premium accordingly.
     *
     * @param baseRate The new base rate.
     */
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
        // Recalculate the premium when base rate is updated
        setQuotePrice(calculatePremium(baseRate, this.riskFactor));
    }

    /**
     * Gets the risk factor influencing the premium.
     *
     * @return The risk factor.
     */
    public double getRiskFactor() {
        return riskFactor;
    }

    /**
     * Sets the risk factor and updates the premium accordingly.
     *
     * @param riskFactor The new risk factor.
     */
    public void setRiskFactor(double riskFactor) {
        this.riskFactor = riskFactor;
        // Recalculate the premium when risk factor is updated
        setQuotePrice(calculatePremium(this.baseRate, riskFactor));
    }

    /**
     * Gets the home associated with the quote.
     *
     * @return The home object.
     */
    public Home getHome() {
        return home;
    }

    /**
     * Sets the home associated with the quote.
     *
     * @param home The home object.
     */
    public void setHome(Home home) {
        this.home = home;
    }
}
