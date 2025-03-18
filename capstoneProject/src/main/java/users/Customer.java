package users;

import objects.Home;
import objects.Vehicle;
import policy.Policy;
import quotes.Quote;
import quotes.QuoteFactory;
import java.time.LocalDate;
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
     */
    public Quote requestHomeQuote(Home home) {
        boolean hasAutoPolicy = policies.stream().anyMatch(p -> p.getPolicyType().equalsIgnoreCase("Auto") && p.getStatus().equals("Active"));
        double discountFactor = hasAutoPolicy ? 0.9 : 1.0; // 10% discount if an auto policy exists

        Quote homeQuote = QuoteFactory.createHomeQuote(quotes.size() + 1, home, 1000.0 * discountFactor);
        quotes.add(homeQuote);
        return homeQuote;
    }

    /**
     * Request a new Auto Insurance Quote.
     */
    public Quote requestAutoQuote(Vehicle vehicle, int driverAge, int accidentCount) {
        boolean hasHomePolicy = policies.stream().anyMatch(p -> p.getPolicyType().equalsIgnoreCase("Home") && p.getStatus().equals("Active"));
        double discountFactor = hasHomePolicy ? 0.9 : 1.0; // 10% discount if a home policy exists

        Quote autoQuote = QuoteFactory.createAutoQuote(quotes.size() + 1, vehicle, driverAge, accidentCount, 1200.0 * discountFactor);
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
        quote.markAsPaid();
        paidQuotes.add(quote);
        quotes.remove(quote);
        System.out.println("Payment received for Quote: " + quote.getQuoteId());
    }

    /**
     * Converts a paid Quote into a Policy.
     */
    public Policy createPolicyFromQuote(Quote quote) {
        if (!paidQuotes.contains(quote)) {
            throw new IllegalStateException("Quote must be paid before converting to a Policy.");
        }

        String policyType = quote.getQuoteType();
        if (policyType.equalsIgnoreCase("Home") && getActivePolicyCount("Home") >= HOME_POLICY_LIMIT) {
            throw new IllegalStateException("Customer has reached active home policy limit.");
        }

        if (policyType.equalsIgnoreCase("Auto") && getActivePolicyCount("Auto") >= AUTO_POLICY_LIMIT) {
            throw new IllegalStateException("Customer has reached active auto policy limit.");
        }

        Policy policy = new Policy("P-" + (policies.size() + 1), quote, policyType);
        policies.add(policy);
        paidQuotes.remove(quote);
        return policy;
    }

    /**
     * Cancels an active policy before expiry.
     */
    public void cancelPolicy(Policy policy) {
        if (!policies.contains(policy)) {
            throw new IllegalArgumentException("Policy does not belong to this customer.");
        }
        if (!policy.getStatus().equalsIgnoreCase("Active")) {
            throw new IllegalStateException("Only active policies can be canceled.");
        }
        policy.setStatus("Cancelled");
        System.out.println("Policy " + policy.getPolicyNumber() + " has been canceled.");
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
