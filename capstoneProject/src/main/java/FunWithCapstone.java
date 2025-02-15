import users.*;
import policy.*;
import quotes.*;
import java.util.*;

/**
 * This class is used to test the functionality of the Capstone Project.
 *
 * Constructs a HomeQuote and AutoQuote, a Home and Auto Policy, a Customer, and an Agent.
 * Uses the displayUserInfo() method to display the information of the Customer and Agent.
 * Expires Quotes at the end.
 */

// TODO: Verify if user input is needed and flesh out how the application should display the information. Verify that objects are being created/used correctly.

public class FunWithCapstone {
    public static void main(String[] args) {
        // Create Quotes
        // Home Quote
        HomeQuote homeQuote = new HomeQuote(101, 0, new Date(), 350000, 1.25, 1.25, 1.0);
        homeQuote.generateQuote();

        // Auto Quote
        AutoQuote autoQuote = new AutoQuote(102, 0, new Date(), 26, 0, 5, 20000);
        autoQuote.generateQuote();

        // Create Policies
        Policy homePolicy = new Policy("A123", homeQuote, "Home");
        Policy autoPolicy = new Policy("B456", autoQuote, "Auto");

        // Create Customer
        Customer customer = new Customer(1, "John Jones", "john.jones@gmail.com");

        // Add policies to the customer's list
        customer.addPolicy(homePolicy);
        customer.addPolicy(autoPolicy);

        // Display Customer Information
        System.out.println("\n===== CUSTOMER INFORMATION =====");
        customer.displayUserInfo();

        // Create Agent
        Agent agent = new Agent(99, "Will Smith", "will.smith@joshtaylorinsurance.com", "Senior Agent");
        System.out.println("\n\n===== AGENT INFORMATION =====");
        agent.displayUserInfo();

        // Agent views customer's policies
        System.out.println("\n\n===== AGENT VIEWING CUSTOMER INFO =====");
        agent.viewCustomerInfo(customer);

        // Expire Quotes
        homeQuote.expireQuote();
        autoQuote.expireQuote();

    }
}



