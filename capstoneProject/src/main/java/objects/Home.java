package objects;

/**
 * Represents a Home.
 * Stores details required for home insurance quotes.
 */
public class Home {
    private final String address;
    private final int yearBuilt;
    private final double homeValue;

    public Home(String address, int yearBuilt, double homeValue) {
        this.address = address;
        this.yearBuilt = yearBuilt;
        this.homeValue = homeValue;
    }

    public String getAddress() {
        return address;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public double getHomeValue() {
        return homeValue;
    }
}