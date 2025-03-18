package quotes;

import objects.Home;
import objects.Vehicle;

/**
 * Quote Factory generates Home and Auto quotes.
 */
public class QuoteFactory {

    public static HomeQuote createHomeQuote(int quoteId, Home home, double baseRate) {
        return new HomeQuote(quoteId, home, baseRate, 1.2);
    }

    public static AutoQuote createAutoQuote(int quoteId, Vehicle vehicle, int driverAge, int accidentCount, double baseRate) {
        return new AutoQuote(quoteId, vehicle, driverAge, accidentCount, baseRate);
    }
}
