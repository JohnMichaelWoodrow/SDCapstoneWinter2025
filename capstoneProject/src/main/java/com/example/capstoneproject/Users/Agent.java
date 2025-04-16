package com.example.capstoneproject.Users;

import jakarta.persistence.*;

/**
 * Represents an Agent entity with attributes for managing insurance agents.
 */
@Entity
public class Agent {
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

    /**
     * Default constructor for Agent.
     */
    public Agent() {}

    /**
     * Constructs a new Agent instance with specified details.
     *
     * @param id       The unique identifier for the agent.
     * @param name     The name of the agent.
     * @param email    The agent's email address.
     * @param user     The associated User entity.
     * @param password The agent's password.
     */
    public Agent(int id, String name, String email, User user, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the agent.
     *
     * @return The agent ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the agent.
     *
     * @param id The agent ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the agent.
     *
     * @return The agent name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the agent.
     *
     * @param name The agent name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the agent.
     *
     * @return The agent email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the agent.
     *
     * @param email The agent email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the agent.
     *
     * @return The agent password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the agent.
     *
     * @param password The agent password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the associated User entity for the agent.
     *
     * @return The User entity.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the associated User entity for the agent.
     *
     * @param user The User entity.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Generates a string representation of the agent object.
     *
     * @return A formatted string describing the agent.
     */
    @Override
    public String toString() {
        return "Agent [ID=" + id + ", Name=" + name + ", Email=" + email + ", Role=" + user.getRole() + "]";
    }
}
