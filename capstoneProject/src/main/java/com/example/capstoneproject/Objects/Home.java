package com.example.capstoneproject.Objects;

import jakarta.persistence.*;

/**
 * Represents a Home entity with various attributes related to home insurance.
 */
@Entity
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private int yearBuilt;
    private double homeValue;
    private String typeOfDwelling;
    private String heatingType;
    private String location;
    private double liabilityLimit;

    /**
     * Default constructor for Home.
     */
    public Home() {}

    /**
     * Constructs a new Home instance with specified details.
     *
     * @param address        The address of the home.
     * @param yearBuilt      The year the home was built.
     * @param homeValue      The estimated value of the home.
     * @param typeOfDwelling The type of dwelling (e.g., house, apartment).
     * @param heatingType    The heating type used in the home.
     * @param location       The location classification of the home.
     * @param liabilityLimit The liability limit for insurance purposes.
     */
    public Home(String address, int yearBuilt, double homeValue, String typeOfDwelling, String heatingType, String location, double liabilityLimit) {
        this.address = address;
        this.yearBuilt = yearBuilt;
        this.homeValue = homeValue;
        this.typeOfDwelling = typeOfDwelling;
        this.heatingType = heatingType;
        this.location = location;
        this.liabilityLimit = liabilityLimit;
    }

    // Getters and setters for all fields

    /**
     * Gets the unique identifier of the home.
     *
     * @return The home ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the home.
     *
     * @param id The home ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the address of the home.
     *
     * @return The home address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the home.
     *
     * @param address The home address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the year the home was built.
     *
     * @return The year built.
     */
    public int getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Sets the year the home was built.
     *
     * @param yearBuilt The year built.
     */
    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    /**
     * Gets the estimated value of the home.
     *
     * @return The home value.
     */
    public double getHomeValue() {
        return homeValue;
    }

    /**
     * Sets the estimated value of the home.
     *
     * @param homeValue The home value.
     */
    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }

    /**
     * Gets the type of dwelling.
     *
     * @return The type of dwelling.
     */
    public String getTypeOfDwelling() {
        return typeOfDwelling;
    }

    /**
     * Sets the type of dwelling.
     *
     * @param typeOfDwelling The type of dwelling.
     */
    public void setTypeOfDwelling(String typeOfDwelling) {
        this.typeOfDwelling = typeOfDwelling;
    }

    /**
     * Gets the heating type used in the home.
     *
     * @return The heating type.
     */
    public String getHeatingType() {
        return heatingType;
    }

    /**
     * Sets the heating type used in the home.
     *
     * @param heatingType The heating type.
     */
    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    /**
     * Gets the location classification of the home.
     *
     * @return The home location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location classification of the home.
     *
     * @param location The home location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the liability limit for insurance purposes.
     *
     * @return The liability limit.
     */
    public double getLiabilityLimit() {
        return liabilityLimit;
    }

    /**
     * Sets the liability limit for insurance purposes.
     *
     * @param liabilityLimit The liability limit.
     */
    public void setLiabilityLimit(double liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    /**
     * Generates a string representation of the home object.
     *
     * @return A formatted string describing the home.
     */
    @Override
    public String toString() {
        return "Address: " + address + ", Year Built: " + yearBuilt + ", Home Value: $" + homeValue +
                ", Dwelling Type: " + typeOfDwelling + ", Heating: " + heatingType +
                ", Location: " + location + ", Liability Limit: $" + liabilityLimit;
    }
}
