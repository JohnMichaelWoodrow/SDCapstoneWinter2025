package quotes;

import java.time.LocalDate;

/**
 * Represents a Quote in the insurance system.
 */
public abstract class Quote {
    protected final int quoteId;
    protected final String quoteType;
    protected final double quotePrice;
    protected final LocalDate expiryDate;
    private boolean isPaid;

    public Quote(int quoteId, String quoteType, double quotePrice) {
        this.quoteId = quoteId;
        this.quoteType = quoteType;
        this.quotePrice = quotePrice;
        this.expiryDate = LocalDate.now().plusDays(30);
        this.isPaid = false; // Default to unpaid
    }

    public int getQuoteId() {return quoteId; }
    public String getQuoteType() { return quoteType; }
    public double getQuotePrice() { return quotePrice; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public boolean isPaid() { return isPaid; }
    public boolean isExpired() { return LocalDate.now().isAfter(expiryDate); }

    public void markAsPaid() {
        if (isExpired()) {
            throw new IllegalStateException("Cannot pay for an expired quote.");
        }
        this.isPaid = true;
    }

    @Override
    public String toString() {
        return "Quote Type: " + quoteType + "\nID: " + quoteId +
                "\nPrice: $" + quotePrice + "\nExpiry Date: " + expiryDate +
                "\nPaid: " + isPaid;
    }
}
