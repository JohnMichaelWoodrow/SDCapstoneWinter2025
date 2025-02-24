package quotes;

import policy.Policy;
import users.Customer;

import java.time.LocalDate;

public abstract class Quote {
    protected int quoteId;
    protected String quoteType; // Home or Auto
    protected double quotePrice;
    protected LocalDate expiryDate;
    protected boolean isPaid;
    protected LocalDate paymentDate;

    public Quote(int quoteId, String quoteType, double quotePrice) {
        this.quoteId = quoteId;
        this.quoteType = quoteType;
        this.quotePrice = quotePrice;
        this.expiryDate = LocalDate.now().plusDays(30); // Quote expires after 30 days
        this.isPaid = false; // Default status: not paid
        this.paymentDate = null; // Payment date starts as null
    }

    // Abstract methods to be implemented in subclasses
    public abstract void generateQuote(Customer customer);
    public abstract void expireQuote();

    // Checks to see if expired and if not, sets isPaid to true and paymentDate to current date
    public void payForQuote() {
        if (LocalDate.now().isAfter(expiryDate)) {
            throw new IllegalStateException("Quote has expired. Please request a new quote.");
        }
        this.isPaid = true;
        this.paymentDate = LocalDate.now(); // Set payment date when paid
    }

    public int getQuoteId() {
        return quoteId;
    }

    public int setQuoteId(int newQuoteId) {
        this.quoteId = newQuoteId;
        return newQuoteId;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public String setQuoteType(String quoteType) {
        this.quoteType = quoteType;
        return quoteType;
    }

    public double getQuotePrice() {
        return quotePrice;
    }

    public double setQuotePrice(double newQuotePrice) {
        this.quotePrice = newQuotePrice;
        return newQuotePrice;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    // Needed to demonstrate expired quote handling
    public void setExpiryDate(LocalDate newExpiryDate) {
        this.expiryDate = newExpiryDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Quote Type: " + quoteType + "\nID: " + quoteId +
                "\nPrice: $" + quotePrice + "\nExpiry Date: " + expiryDate +
                "\nPayment Date: " + (paymentDate != null ? paymentDate : "Not Paid");
    }
}


