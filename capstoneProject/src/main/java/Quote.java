/**
 * This is just some starter/brainstorming type stuff, feel free to change anything as needed
 */

import java.util.Date;

public class Quote {
    private int quoteId;
    private int quotePrice;
    private Date quoteValidityExpiryDate;

    public Quote(int quoteId, int quotePrice, Date quoteValidityExpiryDate) {
        this.quoteId = quoteId;
        this.quotePrice = quotePrice;
        this.quoteValidityExpiryDate = quoteValidityExpiryDate;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public int getQuotePrice() {
        return quotePrice;
    }

    public Date getQuoteValidityExpiryDate() {
        return quoteValidityExpiryDate;
    }

    public void generateQuote() {
        //TODO: Generate quote logic?
    }

    public void expireQuote() {
        //TODO: Expiry logic?
    }

}
