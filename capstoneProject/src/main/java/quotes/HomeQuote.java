package quotes;

import users.Customer;

import java.time.LocalDate;
import java.util.Date;


public class HomeQuote extends Quote {
    private double homeValue;
    private double ageFactor;
    private double heatingFactor;
    private double locationFactor;

    public HomeQuote(int quoteId, double quotePrice, double homeValue, double ageFactor, double heatingFactor, double locationFactor) {
        super(quoteId, "Home", quotePrice); // Set quoteType to "Home"
        this.homeValue = homeValue;
        this.ageFactor = ageFactor;
        this.heatingFactor = heatingFactor;
        this.locationFactor = locationFactor; // How exactly should this be handled? How do we determine urban/rural
    }

    // TODO: Verify the calculation of the quote price
    @Override
    public void generateQuote(Customer customer) {
        double homeValueInThousands = homeValue / 1000.0;
        double additionalPremium = (homeValueInThousands > 250) ? (homeValueInThousands - 250) * 2 : 0;

        double originalPrice = 500 * ageFactor * heatingFactor * locationFactor + additionalPremium;
        this.quotePrice = originalPrice; // Store original price first

        System.out.println("Original Home Insurance Quote: $" + originalPrice); // Show price before discount

        if (customer.hasActiveAutoPolicy(customer)) {
            System.out.println("Applying 10% discount for existing auto policy.");
            this.quotePrice *= 0.9;  // Apply discount
        }

        // Ensure the quote price is properly stored
        setQuotePrice(this.quotePrice);

        System.out.println("Final Home Insurance Quote: $" + this.quotePrice);
    }





    @Override
    public void expireQuote() {
        // TODO: Implement logic for a home quote expiring
        System.out.println("Home Quote with ID " + quoteId + " has expired.");
    }

    @Override
    public String toString() {
        return super.toString() + " (Home Value: $" + homeValue + ")";
    }
}
