/**
 * This is just some starter/brainstorming type stuff, feel free to change anything as needed
 */

import java.util.Date;

public class Policy {
    private int policyId;
    private String policyType;
    private String policyStatus;
    private Date policyExpiryDate;

    public Policy(int policyId, String policyType, String policyStatus, Date policyExpiryDate) {
        this.policyId = policyId;
        this.policyType = policyType;
        this.policyStatus = policyStatus;
        this.policyExpiryDate = policyExpiryDate;
    }

    public int getPolicyId() {
        return policyId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public Date getPolicyExpiryDate() {
        return policyExpiryDate;
    }

    public void RenewPolicy() {
        //TODO: Logic for renew
    }

    public void CancelPolicy() {
        //TODO: logic for cancel
    }
}
