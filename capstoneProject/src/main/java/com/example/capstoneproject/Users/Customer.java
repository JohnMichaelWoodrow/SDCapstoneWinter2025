package com.example.capstoneproject.Users;

import jakarta.persistence.*;
import com.example.capstoneproject.Policy.Policy;
import com.example.capstoneproject.Quotes.Quote;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Customer entity with attributes for managing customer details,
 * including their policies and quotes.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false) // Links to User table
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Policy> policies = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Quote> quotes = new ArrayList<>();

    /**
     * Default constructor for Customer.
     */
    public Customer() {}

    /**
     * Constructs a new Customer instance with specified details.
     *
     * @param id       The unique identifier for the customer.
     * @param name     The name of the customer.
     * @param email    The customer's email address.
     * @param user     The associated User entity.
     * @param password The customer's password.
     */
    public Customer(int id, String name, String email, User user, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the customer.
     *
     * @return The customer ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param id The customer ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The customer name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return The customer email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email The customer email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the customer.
     *
     * @return The customer password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the customer.
     *
     * @param password The customer password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the associated User entity for the customer.
     *
     * @return The User entity.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the associated User entity for the customer.
     *
     * @param user The User entity.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the list of policies linked to the customer.
     *
     * @return The list of policies.
     */
    public List<Policy> getPolicies() {
        return policies;
    }

    /**
     * Sets the list of policies linked to the customer.
     *
     * @param policies The list of policies.
     */
    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    /**
     * Gets the list of quotes linked to the customer.
     *
     * @return The list of quotes.
     */
    public List<Quote> getQuotes() {
        return quotes;
    }

    /**
     * Sets the list of quotes linked to the customer.
     *
     * @param quotes The list of quotes.
     */
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    /**
     * Generates a string representation of the customer object.
     *
     * @return A formatted string describing the customer.
     */
    @Override
    public String toString() {
        return "Customer [ID=" + id + ", Name=" + name + ", Email=" + email + ", Password= " + password + ", User=" + user + "]";
    }
}
