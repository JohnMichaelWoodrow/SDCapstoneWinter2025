package com.example.capstoneproject.Users;

import jakarta.persistence.*;

/**
 * Represents a User entity with attributes specifying the user's role.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String role; // Specifies whether the user is a Customer, Admin, or Agent

    /**
     * Default constructor for User.
     */
    public User() {}

    /**
     * Constructs a new User instance with specified details.
     *
     * @param id   The unique identifier for the user.
     * @param role The role assigned to the user (e.g., Customer, Admin, Agent).
     */
    public User(int id, String role) {
        this.id = id;
        this.role = role;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The user ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the role of the user.
     *
     * @return The user role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The user role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Generates a string representation of the user object.
     *
     * @return A formatted string describing the user.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Role: " + role;
    }
}
