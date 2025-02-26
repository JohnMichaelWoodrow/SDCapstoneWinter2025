package users;

import policy.Policy;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Customer in the insurance system.
 */
public class Customer extends User {
    private List<Policy> policies;

    public Customer(int id, String name, String email) {
        super(id, name, email);
        this.policies = new ArrayList<>();
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void addPolicy(Customer customer, Policy policy) {
        if (policy.getPolicyType().equalsIgnoreCase("Home")) {
            if (!hasActiveHomePolicy(customer)) {
                policies.add(policy);
                System.out.println("Policy added to customer: " + name);
            } else {
                throw new RuntimeException("Customer has reached active home policy limit");
            }
        } else if (policy.getPolicyType().equalsIgnoreCase("Auto")) {
            if (!reachedAutoPolicyLimit(customer)) {
                policies.add(policy);
                System.out.println("Policy added to customer: " + name);
            } else {
                throw new RuntimeException("Customer has reached active auto policy limit");
            }
        }
    }

    public boolean hasActiveAutoPolicy(Customer customer) {
        for (Policy policy : customer.getPolicies()) {
            if (policy.getPolicyType().equalsIgnoreCase("Auto") && policy.getStatus().equalsIgnoreCase("Active")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasActiveHomePolicy(Customer customer) {
        for (Policy policy : customer.getPolicies()) {
            if (policy.getPolicyType().equalsIgnoreCase("Home") && policy.getStatus().equalsIgnoreCase("Active")) {
                return true;
            }
        }
        return false;
    }

    public boolean reachedAutoPolicyLimit(Customer customer) {
        int autoPolicyLimit = 2;
        int customerAutoPolicyCount = 0;
        for (Policy policy : customer.getPolicies()) {
            if (policy.getPolicyType().equalsIgnoreCase("Auto") && policy.getStatus().equalsIgnoreCase("Active")) {
                customerAutoPolicyCount++;
                if (customerAutoPolicyCount == autoPolicyLimit) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Customer Info: " + this);
        if (policies.isEmpty()) {
            System.out.println("No active policies.");
        } else {
            System.out.println("Policies:");
            for (Policy policy : policies) {
                System.out.println(policy);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (Customer)";
    }
}
