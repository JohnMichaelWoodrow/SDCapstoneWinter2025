package com.example.capstoneproject.Requests;

import java.time.LocalDate;

/**
 * Represents a request for creating a Policy.
 * Contains details such as policy number, type, start and end date, premium, and customer information.
 */
public class PolicyRequest {
    private String policyNumber;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private boolean isPaid;
    private double basePremium;
    private double taxRate;
    private int quoteId;
    private int customerId;

    /**
     * Default constructor for PolicyRequest.
     */
    public PolicyRequest() {
    }

    // Getters and setters

    /**
     * Gets the policy number associated with the request.
     *
     * @return The policy number.
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Sets the policy number for the request.
     *
     * @param policyNumber The policy number.
     */
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    /**
     * Gets the type of policy associated with the request.
     *
     * @return The policy type.
     */
    public String getPolicyType() {
        return policyType;
    }

    /**
     * Sets the type of policy for the request.
     *
     * @param policyType The policy type.
     */
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    /**
     * Gets the start date of the policy.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the policy.
     *
     * @param startDate The start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the policy.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the policy.
     *
     * @param endDate The end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the current status of the policy (e.g., Active, Cancelled, Renewed).
     *
     * @return The policy status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the policy.
     *
     * @param status The policy status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Checks whether the policy has been paid.
     *
     * @return True if paid, false otherwise.
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Sets the payment status of the policy.
     *
     * @param isPaid The payment status.
     */
    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * Gets the base premium of the policy.
     *
     * @return The base premium amount.
     */
    public double getBasePremium() {
        return basePremium;
    }

    /**
     * Sets the base premium of the policy.
     *
     * @param basePremium The base premium amount.
     */
    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    /**
     * Gets the applicable tax rate for the policy.
     *
     * @return The tax rate.
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Sets the applicable tax rate for the policy.
     *
     * @param taxRate The tax rate.
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Gets the quote ID associated with the policy request.
     *
     * @return The quote ID.
     */
    public int getQuoteId() {
        return quoteId;
    }

    /**
     * Sets the quote ID for the policy request.
     *
     * @param quoteId The quote ID.
     */
    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * Gets the customer ID associated with the policy request.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for the policy request.
     *
     * @param customerId The customer ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
