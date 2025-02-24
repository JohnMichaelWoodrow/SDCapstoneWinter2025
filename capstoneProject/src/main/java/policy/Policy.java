package policy;

import quotes.Quote;
import java.time.LocalDate;

public class Policy {
    private String policyNumber;
    private Quote quote;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;  // Active, Pending, Cancelled, Expired

    public Policy(String policyNumber, Quote quote, String policyType, LocalDate paymentDate) {
        if (paymentDate == null) {
            throw new IllegalArgumentException("Payment date cannot be null when creating a policy.");
        }
        this.policyNumber = policyNumber;
        this.quote = quote;
        this.policyType = policyType;
        this.startDate = paymentDate;  // Set start date upon payment
        this.endDate = paymentDate.plusYears(1); // Expiry after 1 year
        this.status = "Active";  // Policies only become active after payment confirmation
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

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Policy Number: " + policyNumber + "\nType: " + policyType +
                "\nStatus: " + status + "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate + "\nQuote: " + quote;
    }
}
