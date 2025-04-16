package com.example.capstoneproject.Objects;

import jakarta.persistence.*;

/**
 * Represents a Vehicle entity with attributes related to auto insurance.
 */
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private String model;
    private int year;
    private String vin;

    /**
     * Default constructor for Vehicle.
     */
    public Vehicle() {}

    /**
     * Constructs a new Vehicle instance with specified details.
     *
     * @param make  The manufacturer of the vehicle.
     * @param model The model of the vehicle.
     * @param year  The year the vehicle was manufactured.
     * @param vin   The Vehicle Identification Number (VIN).
     */
    public Vehicle(String make, String model, int year, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the vehicle.
     *
     * @return The vehicle ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the vehicle.
     *
     * @param id The vehicle ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the manufacturer of the vehicle.
     *
     * @return The vehicle make.
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the manufacturer of the vehicle.
     *
     * @param make The vehicle make.
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets the model of the vehicle.
     *
     * @return The vehicle model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the vehicle.
     *
     * @param model The vehicle model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the manufacturing year of the vehicle.
     *
     * @return The vehicle year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the manufacturing year of the vehicle.
     *
     * @param year The vehicle year.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the Vehicle Identification Number (VIN).
     *
     * @return The VIN.
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets the Vehicle Identification Number (VIN).
     *
     * @param vin The VIN.
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Generates a string representation of the vehicle object.
     *
     * @return A formatted string describing the vehicle.
     */
    @Override
    public String toString() {
        return "Make: " + make + ", Model: " + model + ", Year: " + year + ", VIN: " + vin;
    }
}
