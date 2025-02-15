package quotes;

import java.util.Date;

public abstract class Quote {
    protected int quoteId;
    protected String quoteType; // Home or Auto
    protected double quotePrice;
    protected Date expiryDate;

    public Quote(int quoteId, String quoteType, double quotePrice, Date expiryDate) {
        this.quoteId = quoteId;
        this.quoteType = quoteType; // Fixed: Passed via constructor (FunWithCapstone.java).
        this.quotePrice = quotePrice;
        this.expiryDate = expiryDate;
    }

    // Abstract methods to be implemented in subclasses
    public abstract void generateQuote();
    public abstract void expireQuote();

    public int getQuoteId() {
        return quoteId;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public double getQuotePrice() {
        return quotePrice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Quote Type: " + quoteType + "\nID: " + quoteId + "\nPrice: $" + quotePrice + "\nExpiry Date: " + expiryDate;
    }
}

