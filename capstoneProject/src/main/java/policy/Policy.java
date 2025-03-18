package policy;

import quotes.Quote;
import java.time.LocalDate;

/**
 * Represents a Policy in the insurance system.
 */
public class Policy {
    private final String policyNumber;
    private final Quote quote;
    private final String policyType;
    private final LocalDate startDate;
    private LocalDate endDate;
    private String status; // Active, Pending, Cancelled, Expired
    private boolean isPaid;

    /**
     * Constructs a Policy from a paid Quote.
     */
    public Policy(String policyNumber, Quote quote, String policyType) {
        if (!quote.isPaid()) {
            throw new IllegalStateException("Quote must be paid before creating a Policy.");
        }

        this.policyNumber = policyNumber;
        this.quote = quote;
        this.policyType = policyType;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusYears(1);
        this.status = "Active"; // Policies only become active after payment confirmation
        this.isPaid = true;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public Quote getQuote() {
        return quote;
    }

    public String getPolicyType() {
        return policyType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        return this.status = status;
    }

    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Cancels the policy before its expiry date.
     */
    public void cancelPolicy() {
        if (!status.equals("Active")) {
            throw new IllegalStateException("Only active policies can be canceled.");
        }
        this.status = "Cancelled";
        System.out.println("Policy " + policyNumber + " has been canceled.");
    }

    /**
     * Auto-renews the policy if it is still active and not canceled.
     */
    public void renewPolicy() {
        if (!status.equals("Active")) {
            throw new IllegalStateException("Only active policies can be renewed.");
        }
        this.endDate = endDate.plusYears(1);
        System.out.println("Policy " + policyNumber + " has been renewed. New expiry date: " + endDate);
    }

    /**
     * Marks the policy as expired if the end date has passed.
     */
    public void checkExpiration() {
        if (LocalDate.now().isAfter(endDate)) {
            this.status = "Expired";
            System.out.println("Policy " + policyNumber + " has expired.");
        }
    }

    @Override
    public String toString() {
        return "Policy Number: " + policyNumber + "\nType: " + policyType +
                "\nStatus: " + status + "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate + "\nPaid: " + isPaid +
                "\nQuote: " + quote;
    }
}
