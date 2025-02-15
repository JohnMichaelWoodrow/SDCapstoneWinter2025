package quotes;

import java.util.Date;

public class AutoQuote extends Quote {
    private int driverAge;
    private int accidentCount;
    private int vehicleAge;
    private double carValue;

    public AutoQuote(int quoteId, double quotePrice, Date expiryDate, int driverAge, int accidentCount, int vehicleAge, double carValue) {
        super(quoteId, "Auto", quotePrice, expiryDate);
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
        this.vehicleAge = vehicleAge;
        this.carValue = carValue;
    }

    @Override
    public void generateQuote() {
        double ageFactor = (driverAge < 25) ? 2.0 : 1.0;
        double accidentFactor = (accidentCount == 0) ? 1.0 : (accidentCount == 1) ? 1.25 : 2.5;
        double vehicleAgeFactor = (vehicleAge > 10) ? 2.0 : (vehicleAge > 5) ? 1.5 : 1.0;
        this.quotePrice = 750 * ageFactor * vehicleAgeFactor * accidentFactor;
        System.out.println("Generated Auto Insurance Quote: $" + quotePrice);
    }


    @Override
    public void expireQuote() {
        System.out.println("Auto Quote with ID " + quoteId + " has expired.");
    }

    @Override
    public String toString() {
        return super.toString() + " (Driver Age: " + driverAge + ", Accidents: " + accidentCount + ")";
    }
}
