package com.example.capstoneproject.Quotes;

import jakarta.persistence.*;
import com.example.capstoneproject.Users.Customer;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents an abstract Quote entity used as a base for different insurance quotes.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quoteType;
    private double quotePrice;
    private LocalDate expiryDate;
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    /**
     * Default constructor for Quote (needed for deserialization).
     */
    public Quote() {}

    /**
     * Constructs a new Quote instance with specified details.
     *
     * @param id         The unique identifier for the quote.
     * @param quoteType  The type of quote (e.g., Auto, Home).
     * @param quotePrice The calculated price of the quote.
     */
    public Quote(int id, String quoteType, double quotePrice) {
        this.id = id;
        this.quoteType = quoteType;
        this.quotePrice = quotePrice;
        this.expiryDate = LocalDate.now().plusDays(30);
        this.isPaid = false;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the quote.
     *
     * @return The quote ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the quote.
     *
     * @param id The quote ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the type of the quote.
     *
     * @return The quote type.
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * Sets the type of the quote.
     *
     * @param quoteType The quote type.
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    /**
     * Gets the calculated price of the quote.
     *
     * @return The quote price.
     */
    public double getQuotePrice() {
        return quotePrice;
    }

    /**
     * Sets the calculated price of the quote.
     *
     * @param quotePrice The quote price.
     */
    public void setQuotePrice(double quotePrice) {
        this.quotePrice = quotePrice;
    }

    /**
     * Gets the expiry date of the quote.
     *
     * @return The expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the quote.
     *
     * @param expiryDate The expiry date.
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Checks if the quote has been paid for.
     *
     * @return True if paid, otherwise false.
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Marks the quote as paid.
     *
     * @param paid The new payment status.
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Gets the ID of the customer associated with this quote.
     *
     * @return The customer ID, or null if not assigned.
     */
    @Transient
    public Integer getCustomerId() {
        return customer != null ? customer.getId() : null;
    }

    /**
     * Sets the customer associated with this quote.
     *
     * @param customer The customer object.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Determines if the quote has expired.
     *
     * @return True if expired, otherwise false.
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    /**
     * Marks the quote as paid, ensuring it is not expired.
     *
     * @throws IllegalStateException if the quote is expired.
     */
    public void markAsPaid() {
        if (isExpired()) {
            throw new IllegalStateException("Cannot pay for an expired quote.");
        }
        this.isPaid = true;
    }

    /**
     * Generates a string representation of the quote object.
     *
     * @return A formatted string describing the quote.
     */
    @Override
    public String toString() {
        return "Quote Type: " + quoteType + ", ID: " + id + ", Price: $" + quotePrice +
                ", Expiry Date: " + expiryDate + ", Paid: " + isPaid;
    }
}
