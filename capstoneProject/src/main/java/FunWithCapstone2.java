import users.*;
import policy.*;
import quotes.*;
import java.util.*;

/**
 * This class is used to test the functionality of the Capstone Project.
 *
 * Tests various scenarios for creating Quotes, Policies, Customers, and Agents.
 * Ensures objects are correctly created and used.
 * No user input or database connection required.
 */
public class FunWithCapstone2 {

    public static void main(String[] args) {
        System.out.println("===== CAPSTONE PROJECT TESTS =====");

        testHomeQuoteCreation();
        testAutoQuoteCreation();
        testPolicyCreation();
        testCustomerCreation();
        testAgentCreation();
        testQuoteExpiration();
        testPolicyAutoRenewal();
        testPolicyBundleDiscount();
        testInvalidScenarios();

        System.out.println("\n===== TESTS COMPLETED =====");
    }

    // Test: Home Quote Generation
    private static void testHomeQuoteCreation() {
        System.out.println("\n[Test] Home Quote Creation");
        HomeQuote homeQuote = new HomeQuote(101, 0, new Date(), 350000, 1.25, 1.25, 1.0);
        homeQuote.generateQuote();
        System.out.println("Expected: Home Quote with calculated premium.");
        System.out.println("Actual: " + homeQuote);
    }

    // Test: Auto Quote Generation
    private static void testAutoQuoteCreation() {
        System.out.println("\n[Test] Auto Quote Creation");
        AutoQuote autoQuote = new AutoQuote(102, 0, new Date(), 26, 0, 5, 20000);
        autoQuote.generateQuote();
        System.out.println("Expected: Auto Quote with calculated premium.");
        System.out.println("Actual: " + autoQuote);
    }

    // Test: Policy Creation
    private static void testPolicyCreation() {
        System.out.println("\n[Test] Policy Creation");
        HomeQuote homeQuote = new HomeQuote(103, 0, new Date(), 500000, 1.0, 1.5, 1.0);
        Policy homePolicy = new Policy("H001", homeQuote, "Home");

        AutoQuote autoQuote = new AutoQuote(104, 0, new Date(), 30, 1, 2, 15000);
        Policy autoPolicy = new Policy("A001", autoQuote, "Auto");

        System.out.println("Expected: Policies with associated quotes.");
        System.out.println("Home Policy: " + homePolicy);
        System.out.println("Auto Policy: " + autoPolicy);
    }

    // Test: Customer Creation & Adding Policies
    private static void testCustomerCreation() {
        System.out.println("\n[Test] Customer Creation & Policy Association");
        Customer customer = new Customer(1, "John Jones", "john.jones@gmail.com");

        HomeQuote homeQuote = new HomeQuote(105, 0, new Date(), 400000, 1.0, 1.2, 1.0);
        Policy homePolicy = new Policy("H002", homeQuote, "Home");

        customer.addPolicy(homePolicy);

        System.out.println("Expected: Customer with one home policy.");
        System.out.println("Actual: " + customer);
    }

    // Test: Agent Creation
    private static void testAgentCreation() {
        System.out.println("\n[Test] Agent Creation");
        Agent agent = new Agent(99, "Will Smith", "will.smith@insurance.com", "Senior Agent");

        System.out.println("Expected: Agent profile with email and title.");
        System.out.println("Actual: " + agent);
    }

    // Test: Quote Expiration
    private static void testQuoteExpiration() {
        System.out.println("\n[Test] Quote Expiration");
        HomeQuote homeQuote = new HomeQuote(106, 0, new Date(), 300000, 1.1, 1.3, 1.0);
        AutoQuote autoQuote = new AutoQuote(107, 0, new Date(), 35, 2, 4, 18000);

        System.out.println("Expected: Quotes should be expired.");
        homeQuote.expireQuote();
        autoQuote.expireQuote();
    }

    // Test: Policy Auto-Renewal
    private static void testPolicyAutoRenewal() {
        System.out.println("\n[Test] Policy Auto-Renewal");
        HomeQuote homeQuote = new HomeQuote(108, 0, new Date(), 450000, 1.0, 1.4, 1.0);
        Policy policy = new Policy("H003", homeQuote, "Home");

        System.out.println("Expected: Policy should auto-renew if not canceled.");
        System.out.println("Policy Status: " + policy.getStatus());
    }

    // Test: Policy Bundle Discount (Auto + Home)
    // TODO: Need to implement bundle discount logic
    private static void testPolicyBundleDiscount() {
        System.out.println("\n[Test] Policy Bundle Discount");

        HomeQuote homeQuote = new HomeQuote(109, 0, new Date(), 350000, 1.0, 1.2, 1.0);
        Policy homePolicy = new Policy("H004", homeQuote, "Home");

        AutoQuote autoQuote = new AutoQuote(110, 0, new Date(), 27, 0, 3, 22000);
        Policy autoPolicy = new Policy("A002", autoQuote, "Auto");

        Customer customer = new Customer(2, "Alice Smith", "alice.smith@example.com");
        customer.addPolicy(homePolicy);
        customer.addPolicy(autoPolicy);

//        System.out.println("Expected: Policies should have a bundle discount.");
//        System.out.println("Home Policy Premium: " + homePolicy.calculatePremium());
//        System.out.println("Auto Policy Premium: " + autoPolicy.calculatePremium());
    }

    // Test: Invalid or Edge Case Scenarios
    // TODO: Need more valid error handling for these scenarios + (need to flesh out where error handling should be at this point should it be here in the test class?)
    private static void testInvalidScenarios() {
        System.out.println("\n[Test] Invalid & Edge Case Scenarios");

        try {
            System.out.println("Attempting to create policy with null quote...");
            Policy invalidPolicy = new Policy("X001", null, "Home");
            System.out.println("Unexpected: Policy should not be created.");
        } catch (Exception e) {
            System.out.println("Expected: Error caught. " + e.getMessage());
        }

        try {
            System.out.println("Attempting to create customer with invalid email...");
            Customer invalidCustomer = new Customer(3, "Bob Builder", "invalid-email");
            System.out.println("Unexpected: Customer should not be created.");
        } catch (Exception e) {
            System.out.println("Expected: Error caught. " + e.getMessage());
        }

        try {
            System.out.println("Attempting to create quote with negative home value...");
            HomeQuote invalidQuote = new HomeQuote(111, 0, new Date(), -100000, 1.0, 1.0, 1.0);
            invalidQuote.generateQuote();
            System.out.println("Unexpected: Quote should not be created.");
        } catch (Exception e) {
            System.out.println("Expected: Error caught. " + e.getMessage());
        }
    }
}
