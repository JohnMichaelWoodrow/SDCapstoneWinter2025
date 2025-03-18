package objects;

/**
 * Represents a Vehicle.
 * Stores details required for auto insurance quotes.
 */
public class Vehicle {
    private final String make;
    private final String model;
    private final int year;
    private final String vin;

    public Vehicle(String make, String model, int year, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getVin() {
        return vin;
    }
}