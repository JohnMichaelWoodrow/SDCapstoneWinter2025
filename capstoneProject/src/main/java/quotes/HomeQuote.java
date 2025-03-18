package quotes;
import objects.Home;

/**
 * Represents a HomeQuote in the insurance system.
 */
public class HomeQuote extends Quote {
    private final double homeValue;
    private final double riskFactor;

    public HomeQuote(int quoteId, Home home, double baseRate, double riskFactor) {
        super(quoteId, "Home", baseRate * riskFactor);
        this.homeValue = home.getHomeValue();
        this.riskFactor = riskFactor;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHome Value: $" + homeValue + "\nRisk Factor: " + riskFactor;
    }
}
