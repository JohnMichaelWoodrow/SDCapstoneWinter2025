package com.example.capstoneproject.Requests;

import java.time.LocalDate;

/**
 * Represents a request for creating a HomeQuote.
 * Contains details such as home ID, base rate, risk factor, quote price, and expiry date.
 */
public class HomeQuoteRequest {
    private int homeId;
    private double baseRate;
    private double riskFactor;
    private int customerId;
    private double quotePrice;
    private boolean paid;
    private LocalDate expiryDate;

    /**
     * Default constructor for HomeQuoteRequest.
     */
    public HomeQuoteRequest() {
    }

    // Getters and setters

    /**
     * Gets the home ID associated with the quote request.
     *
     * @return The home ID.
     */
    public int getHomeId() {
        return homeId;
    }

    /**
     * Sets the home ID for the quote request.
     *
     * @param homeId The home ID.
     */
    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    /**
     * Gets the base rate for the home quote.
     *
     * @return The base rate.
     */
    public double getBaseRate() {
        return baseRate;
    }

    /**
     * Sets the base rate for the home quote.
     *
     * @param baseRate The base rate.
     */
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * Gets the risk factor influencing the home quote.
     *
     * @return The risk factor.
     */
    public double getRiskFactor() {
        return riskFactor;
    }

    /**
     * Sets the risk factor for the home quote.
     *
     * @param riskFactor The risk factor.
     */
    public void setRiskFactor(double riskFactor) {
        this.riskFactor = riskFactor;
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
     * Gets the final calculated price of the home quote.
     *
     * @return The quote price.
     */
    public double getQuotePrice() {
        return quotePrice;
    }

    /**
     * Sets the final calculated price of the home quote.
     *
     * @param quotePrice The quote price.
     */
    public void setQuotePrice(double quotePrice) {
        this.quotePrice = quotePrice;
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
     * Gets the expiry date of the home quote.
     *
     * @return The expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date for the home quote.
     *
     * @param expiryDate The expiry date.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Generates a string representation of the HomeQuoteRequest object.
     *
     * @return A formatted string describing the request.
     */
    @Override
    public String toString() {
        return "HomeQuoteRequest{" +
                "homeId=" + homeId +
                ", baseRate=" + baseRate +
                ", riskFactor=" + riskFactor +
                ", customerId=" + customerId +
                ", quotePrice=" + quotePrice +
                ", paid=" + paid +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
