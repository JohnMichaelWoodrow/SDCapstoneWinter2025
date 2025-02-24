package users;

public class Admin extends User {
    private String role;

    public Admin(int id, String name, String email, String role) {
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

    public void viewAgentInfo(Agent agent) {
        System.out.println("\nViewing Agent Information:");
        agent.displayUserInfo();
    }

    public void updateCustomerPolicy(Customer customer) {
        // TODO: Logic for updating customer policy? ****AFTER CONSOLE APPLICATION****
    }

    public void editUser(User user) {
        // TODO: Logic for editing user? ****AFTER CONSOLE APPLICATION****
    }

    public void removeUser(User user) {
        // TODO: Logic for removing user? ****AFTER CONSOLE APPLICATION****
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Admin Info: " + this);
    }

    @Override
    public String toString() {
        return super.toString() + " (Admin - Role: " + role + ")";
    }
}
