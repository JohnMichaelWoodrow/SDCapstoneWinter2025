package com.example.capstoneproject.Policy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.capstoneproject.Users.Customer;

import java.time.LocalDate;

/**
 * Represents a Policy entity that defines insurance policy details.
 */
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String policyNumber;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // "Active", "Cancelled", "Renewed"
    private boolean isPaid;
    private double basePremium;
    private double taxRate; // 15% HST
    private double totalPremium;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    private int quoteId; // Foreign key representing the associated Quote

    /**
     * Default constructor for Policy.
     */
    public Policy() {}

    /**
     * Constructs a new Policy instance with specified details.
     *
     * @param policyNumber The unique policy number.
     * @param quoteId      The associated Quote ID.
     * @param policyType   The type of policy (e.g., Auto, Home).
     * @param basePremium  The base premium amount.
     * @param taxRate      The applicable tax rate.
     */
    public Policy(String policyNumber, int quoteId, String policyType, double basePremium, double taxRate) {
        this.policyNumber = policyNumber;
        this.quoteId = quoteId;
        this.policyType = policyType;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusYears(1); // Policies are 1 year long
        this.status = "Active";
        this.isPaid = true;
        this.basePremium = basePremium;
        this.taxRate = taxRate;
        calculateTotalPremium();
    }

    /**
     * Calculates the total premium based on the base premium and tax rate.
     */
    private void calculateTotalPremium() {
        this.totalPremium = (double) Math.round(basePremium * (1 + taxRate));
    }

    /**
     * Renews the policy by extending its end date and updating its status.
     *
     * @throws IllegalStateException if the policy is not active.
     */
    public void renewPolicy() {
        if (!status.equals("Active")) {
            throw new IllegalStateException("Only active policies can be renewed.");
        }
        this.endDate = endDate.plusYears(1);
        this.status = "Renewed";
        System.out.println("Policy " + policyNumber + " renewed. New end date: " + endDate);
    }

    /**
     * Cancels the policy by changing its status.
     *
     * @throws IllegalStateException if the policy is not active.
     */
    public void cancelPolicy() {
        if (!status.equals("Active")) {
            throw new IllegalStateException("Only active policies can be canceled.");
        }
        this.status = "Cancelled";
        System.out.println("Policy " + policyNumber + " has been canceled.");
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
        calculateTotalPremium();
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
        calculateTotalPremium();
    }

    public double getTotalPremium() {
        return totalPremium;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    @Transient
    public Integer getCustomerId() {
        return customer != null ? customer.getId() : null;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Generates a string representation of the policy object.
     *
     * @return A formatted string describing the policy.
     */
    @Override
    public String toString() {
        return "Policy {" +
                "id=" + id +
                ", policyNumber='" + policyNumber + '\'' +
                ", policyType='" + policyType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", isPaid=" + isPaid +
                ", basePremium=" + basePremium +
                ", taxRate=" + taxRate +
                ", totalPremium=" + totalPremium +
                ", quoteId=" + quoteId +
                ", customer=" + (customer != null ? customer.toString() : "null") +
                '}';
    }
}
