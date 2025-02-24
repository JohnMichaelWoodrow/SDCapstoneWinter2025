package quotes;

import users.Customer;

import java.time.LocalDate;
import java.util.Date;

public class AutoQuote extends Quote {
    private int driverAge;
    private int accidentCount;
    private int vehicleAge;
    private double carValue;

    public AutoQuote(int quoteId, double quotePrice, int driverAge, int accidentCount, int vehicleAge, double carValue) {
        super(quoteId, "Auto", quotePrice);
        this.driverAge = driverAge;
        this.accidentCount = accidentCount;
        this.vehicleAge = vehicleAge;
        this.carValue = carValue;
    }

    @Override
    public void generateQuote(Customer customer) {
        double ageFactor = (driverAge < 25) ? 2.0 : 1.0;
        double accidentFactor = (accidentCount == 0) ? 1.0 : (accidentCount == 1) ? 1.25 : 2.5;
        double vehicleAgeFactor = (vehicleAge > 10) ? 2.0 : (vehicleAge > 5) ? 1.5 : 1.0;

        double originalPrice = 750 * ageFactor * vehicleAgeFactor * accidentFactor;
        this.quotePrice = originalPrice; // Store original price first

        System.out.println("Original Auto Insurance Quote: $" + originalPrice); // Show price before discount

        if (customer.hasActiveHomePolicy(customer)) {
            System.out.println("Applying 10% discount for existing home policy.");
            this.quotePrice *= 0.9;  // Apply discount
        }

        // Ensure the quote price is properly stored
        setQuotePrice(this.quotePrice);

        System.out.println("Final Auto Insurance Quote: $" + this.quotePrice);
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
