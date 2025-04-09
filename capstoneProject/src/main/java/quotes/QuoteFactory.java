package quotes;

import objects.Home;
import objects.Vehicle;
import users.Customer;

/**
 * Quote Factory generates Home and Auto quotes.
 */
public class QuoteFactory {

    /**
     * Creates a Home Insurance Quote.
     * Determines discounts based on the customer's existing policies.
     */
    public static HomeQuote createHomeQuote(Customer customer, Home home) {
        boolean hasAutoPolicy = customer.getPolicies().stream().anyMatch(p -> p.getPolicyType().equalsIgnoreCase("Auto") && p.getStatus().equals("Active"));

        double baseRate = 1000.0;
        double discountFactor = hasAutoPolicy ? 0.9 : 1.0; // 10% discount if auto policy exists
        double riskFactor = 1.2; // Could be dynamic in the future

        return new HomeQuote(customer.getQuotes().size() + 1, home, baseRate * discountFactor, riskFactor);
    }

    /**
     * Creates an Auto Insurance Quote.
     * Determines discounts based on the customer's existing policies.
     */
    public static AutoQuote createAutoQuote(Customer customer, Vehicle vehicle, int driverAge, int accidentCount) {
        boolean hasHomePolicy = customer.getPolicies().stream().anyMatch(p -> p.getPolicyType().equalsIgnoreCase("Home") && p.getStatus().equals("Active"));

        double baseRate = 1200.0;
        double discountFactor = hasHomePolicy ? 0.9 : 1.0; // 10% discount if home policy exists
        double riskFactor = getRiskFactor(driverAge, accidentCount);

        return new AutoQuote(customer.getQuotes().size() + 1, vehicle, driverAge, accidentCount, baseRate * riskFactor * discountFactor);
    }

    /**
     * Determines the risk factor based on the driver's age and accident history.
     */
    private static double getRiskFactor(int driverAge, int accidentCount) {
        double ageFactor = (driverAge < 25) ? 2.0 : 1.0;
        double accidentFactor = (accidentCount == 0) ? 1.0 : (accidentCount == 1) ? 1.25 : 2.5;
        return ageFactor * accidentFactor;
    }
}
