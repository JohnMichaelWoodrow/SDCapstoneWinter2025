/**
 * This is just some starter/brainstorming type stuff, feel free to change anything as needed
 */

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String customerName;
    private List<Policy> customerPoliciesList;

    public Customer(int customerID, String customerName, List<Policy> customerPoliciesList) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPoliciesList = customerPoliciesList;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Policy> getCustomerPoliciesList() {
        return customerPoliciesList;
    }

    public void register() {
        //TODO: Logic for register?
    }

    public void login() {
        //TODO: Logic for login?
    }
}
