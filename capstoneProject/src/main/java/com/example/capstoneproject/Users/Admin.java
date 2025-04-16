package com.example.capstoneproject.Users;

import jakarta.persistence.*;

/**
 * Represents an Admin entity with attributes for managing administrative users.
 */
@Entity
public class Admin {
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
     * Default constructor for Admin.
     */
    public Admin() {}

    /**
     * Constructs a new Admin instance with specified details.
     *
     * @param id       The unique identifier for the admin.
     * @param name     The name of the admin.
     * @param email    The admin's email address.
     * @param user     The associated User entity.
     * @param password The admin's password.
     */
    public Admin(int id, String name, String email, User user, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the admin.
     *
     * @return The admin ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the admin.
     *
     * @param id The admin ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the admin.
     *
     * @return The admin name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the admin.
     *
     * @param name The admin name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the admin.
     *
     * @return The admin email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the admin.
     *
     * @param email The admin email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the admin.
     *
     * @return The admin password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the admin.
     *
     * @param password The admin password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the associated User entity for the admin.
     *
     * @return The User entity.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the associated User entity for the admin.
     *
     * @param user The User entity.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Generates a string representation of the admin object.
     *
     * @return A formatted string describing the admin.
     */
    @Override
    public String toString() {
        return "Admin [ID=" + id + ", Name=" + name + ", Email=" + email + ", Role=" + user.getRole() + "]";
    }
}
