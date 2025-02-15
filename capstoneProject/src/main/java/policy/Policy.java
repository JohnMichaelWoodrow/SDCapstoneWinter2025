package policy;

import quotes.Quote;

public class Policy {
    private String policyNumber;
    private Quote quote;
    private String policyType;
    private String status;     // Active, Pending, Cancelled, Expired

    public Policy(String policyNumber, Quote quote, String policyType) {
        this.policyNumber = policyNumber;
        this.quote = quote;
        this.policyType = policyType;
        this.status = "Pending"; // Later will be dependent on payment (Active upon payment confirmation). For Console Application should this just be Active???????????????????????????????????????????????????????????????????????????????
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Policy Number: " + policyNumber + "\nType: " + policyType + "\nStatus: " + status + "\nQuote: " + quote;
    }
}
