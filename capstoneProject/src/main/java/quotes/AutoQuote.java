package quotes;
import objects.Vehicle;

/**
 * Represents a AutoQuote in the insurance system.
 */
public class AutoQuote extends Quote {
    private final int driverAge;
    private final int accidentCount;
    private final double vehicleValue;

    public AutoQuote(int quoteId, Vehicle vehicle, int driverAge, int accidentCount, double baseRate) {
        super(quoteId, "Auto", baseRate * getRiskFactor(driverAge, accidentCount));
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
        this.vehicleValue = vehicle.getYear();  // Assuming value is based on age
    }

    private static double getRiskFactor(int driverAge, int accidentCount) {
        double ageFactor = (driverAge < 25) ? 2.0 : 1.0;
        double accidentFactor = (accidentCount == 0) ? 1.0 : (accidentCount == 1) ? 1.25 : 2.5;
        return ageFactor * accidentFactor;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDriver Age: " + driverAge + "\nAccident Count: " + accidentCount;
    }
}

