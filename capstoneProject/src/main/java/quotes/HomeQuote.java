package quotes;

import java.util.Date;

public class HomeQuote extends Quote {
    private double homeValue;
    private double ageFactor;
    private double heatingFactor;
    private double locationFactor;

    public HomeQuote(int quoteId, double quotePrice, Date expiryDate, double homeValue, double ageFactor, double heatingFactor, double locationFactor) {
        super(quoteId, "Home", quotePrice, expiryDate); // Set quoteType to "Home"
        this.homeValue = homeValue;
        this.ageFactor = ageFactor;
        this.heatingFactor = heatingFactor;
        this.locationFactor = locationFactor; // How exactly should this be handled? How do we determine urban/rural
    }

    // TODO: Verify the calculation of the quote price
    @Override
    public void generateQuote() {
        // Convert full home value to thousands
        double homeValueInThousands = homeValue / 1000.0;
        // Calculate additional premium for amounts above $250,000
        double additionalPremium = (homeValueInThousands > 250) ? (homeValueInThousands - 250) * 2 : 0;
        // Base premium calculation with all risk factors
        this.quotePrice = 500 * ageFactor * heatingFactor * locationFactor + additionalPremium;
        System.out.println("Generated Home Insurance Quote: $" + quotePrice);
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
