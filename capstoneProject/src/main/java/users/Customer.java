package users;

import objects.Home;
import objects.Vehicle;
import policy.Policy;
import policy.PolicyFactory;
import quotes.Quote;
import quotes.QuoteFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Customer in the insurance system.
 */
public class Customer extends User {
    private static final int AUTO_POLICY_LIMIT = 2; // Max 2 auto policies
    private static final int HOME_POLICY_LIMIT = 1; // Max 1 home policy
    private List<Policy> policies;
    private List<Quote> quotes;
    private List<Quote> paidQuotes;

    public Customer(int id, String name, String email) {
        super(id, name, email);
        this.policies = new ArrayList<>();
        this.quotes = new ArrayList<>();
        this.paidQuotes = new ArrayList<>();
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public List<Quote> getPaidQuotes() {
        return paidQuotes;
    }

    /**
     * Request a new Home Insurance Quote.
     * The discount logic is handled inside `QuoteFactory`.
     */
    public Quote requestHomeQuote(Home home) {
        Quote homeQuote = QuoteFactory.createHomeQuote(this, home);
        quotes.add(homeQuote);
        return homeQuote;
    }

    /**
     * Request a new Auto Insurance Quote.
     * The discount logic is handled inside `QuoteFactory`.
     */
    public Quote requestAutoQuote(Vehicle vehicle, int driverAge, int accidentCount) {
        Quote autoQuote = QuoteFactory.createAutoQuote(this, vehicle, driverAge, accidentCount);
        quotes.add(autoQuote);
        return autoQuote;
    }

    /**
     * Simulates the customer making a payment for a Quote.
     * Once paid, the quote is moved to the paid list and can be converted into a Policy.
     */
    public void makePayment(Quote quote) {
        if (!quotes.contains(quote)) {
            throw new IllegalArgumentException("Quote does not exist for this customer.");
        }
        if (quote.isExpired()) {
            throw new IllegalStateException("Cannot pay for an expired quote.");
        }
        quote.markAsPaid();
        paidQuotes.add(quote);
        quotes.remove(quote);
        System.out.println("Payment received for Quote: " + quote.getQuoteId());
    }

    /**
     * Converts a paid Quote into a Policy.
     */
    public Policy createPolicyFromQuote(Quote quote) {
        return PolicyFactory.createPolicy(this, quote);
    }

    /**
     * Returns the number of active policies of a given type.
     */
    private int getActivePolicyCount(String policyType) {
        return (int) policies.stream().filter(p -> p.getPolicyType().equalsIgnoreCase(policyType) && p.getStatus().equalsIgnoreCase("Active")).count();
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Customer Info: " + this);
        System.out.println("Active Policies: " + policies.size());
        policies.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return super.toString() + " (Customer)";
    }
}
