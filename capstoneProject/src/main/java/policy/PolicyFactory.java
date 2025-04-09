package policy;

import quotes.Quote;
import users.Customer;

/**
 * Factory for creating policies from paid quotes.
 */
public class PolicyFactory {

    /**
     * Creates a new policy from a paid Quote.
     * Ensures policy limits are respected before creation.
     */
    public static Policy createPolicy(Customer customer, Quote quote) {
        if (!customer.getPaidQuotes().contains(quote)) {
            throw new IllegalStateException("Quote must be paid before converting to a Policy.");
        }

        String policyType = quote.getQuoteType();

        if (policyType.equalsIgnoreCase("Home") && getActivePolicyCount(customer, "Home") >= 1) {
            throw new IllegalStateException("Customer has reached active home policy limit.");
        }

        if (policyType.equalsIgnoreCase("Auto") && getActivePolicyCount(customer, "Auto") >= 2) {
            throw new IllegalStateException("Customer has reached active auto policy limit.");
        }

        Policy policy = new Policy("P-" + (customer.getPolicies().size() + 1), quote.getQuoteId(), policyType);
        customer.getPolicies().add(policy);
        customer.getPaidQuotes().remove(quote);
        return policy;
    }

    /**
     * Returns the number of active policies of a given type for a customer.
     */
    private static int getActivePolicyCount(Customer customer, String policyType) {
        return (int) customer.getPolicies().stream().filter(p -> p.getPolicyType().equalsIgnoreCase(policyType) && p.getStatus().equalsIgnoreCase("Active")).count();
    }
}
