package users;

public class Agent extends User {
    private String role;

    public Agent(int id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String setRole(String role) {
        return this.role = role;
    }

    public void viewCustomerInfo(Customer customer) {
        System.out.println("\nViewing Customer Information:");
        customer.displayUserInfo();
    }

    public void updateCustomerPolicy(Customer customer) {
        // TODO: Logic for updating customer policy? ****AFTER CONSOLE APPLICATION****
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Agent Info: " + this);
    }

    @Override
    public String toString() {
        return super.toString() + " (Agent - Role: " + role + ")";
    }
}
