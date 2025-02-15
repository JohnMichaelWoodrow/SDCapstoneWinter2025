package users;

import policy.Policy;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Policy> policies;

    public Customer(int id, String name, String email) {
        super(id, name, email);
        this.policies = new ArrayList<>();
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void addPolicy(Policy policy) {
        policies.add(policy);
        System.out.println("Policy added to customer " + name);
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
