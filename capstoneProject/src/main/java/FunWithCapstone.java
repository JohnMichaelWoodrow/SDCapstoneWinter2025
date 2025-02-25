/**
 * This class is used to test the functionality of the Capstone Project.
 *
 * Tests for various scenarios are included to ensure the project is working as expected.
 *
 */
import users.*;
import policy.*;
import quotes.*;

import java.time.LocalDate;

/**
 * This class tests the functionality of the Capstone Project.
 */
public class FunWithCapstone {
    public static void main(String[] args) {
        System.out.println("===== CAPSTONE TESTS =====");

        testHomeQuoteCreation();
        testAutoQuoteCreation();
        testQuotePaymentAndPolicyCreation();
        testQuoteExpirationHandling();
        testCustomerCreation();
        testAgentCreation();
        testAgentCustomerInteraction();
        testHomeAutoDiscounts();
        testMultipleAutoPolicies();
        testExceedingAutoPolicyLimits();
        testExceedingHomePolicyLimits();
        testAutoQuoteVariations();
        testHomeQuoteVariations();
        testPolicyCancellation();
        testInvalidScenarios();

        System.out.println("\n===== TESTS COMPLETED =====");
    }

    // Home Quote Generation
    private static void testHomeQuoteCreation() {
        System.out.println("\n[Test] Home Quote Creation");
        Customer customer = new Customer(1, "John Jones", "john.jones@gmail.com");
        HomeQuote homeQuote = new HomeQuote(101, 0, 350000, 1.25, 1.25, 1.0);
        homeQuote.generateQuote(customer);
        System.out.println("Actual: " + homeQuote);
    }

    // Auto Quote Generation
    private static void testAutoQuoteCreation() {
        System.out.println("\n[Test] Auto Quote Creation");
        Customer customer = new Customer(2, "Alice Smith", "alice.smith@example.com");
        AutoQuote autoQuote = new AutoQuote(102, 0, 26, 0, 5, 20000);
        autoQuote.generateQuote(customer);
        System.out.println("Actual: " + autoQuote);
    }

    // Paying for a Quote and Converting to a Policy
    private static void testQuotePaymentAndPolicyCreation() {
        System.out.println("\n[Test] Quote Payment & Policy Creation");
        Customer customer = new Customer(3, "Michael Johnson", "michael.johnson@gmail.com");
        Quote homeQuote = new HomeQuote(201, 0, 350000, 1.1, 1.2, 1.0);
        homeQuote.generateQuote(customer);
        homeQuote.payForQuote();
        Policy homePolicy = new Policy("H-001", homeQuote, "Home", homeQuote.getPaymentDate());
        customer.addPolicy(customer,homePolicy);
        customer.displayUserInfo();
    }

    // Handling Expired Quotes
    private static void testQuoteExpirationHandling() {
        System.out.println("\n[Test] Quote Expiration Handling");
        Quote expiredQuote = new HomeQuote(202, 0, 250000, 1.0, 1.2, 1.0);
        expiredQuote.setExpiryDate(LocalDate.now().minusDays(31));
        System.out.println("Expired Quote: Expiry Date - " + expiredQuote.getExpiryDate());
        try {
            expiredQuote.payForQuote();
        } catch (IllegalStateException e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }

    // Customer Creation
    private static void testCustomerCreation() {
        System.out.println("\n[Test] Customer Creation");
        Customer customer = new Customer(4, "Emily Smith", "emily.smith@gmail.com");
        customer.displayUserInfo();
    }

    // Agent Creation
    private static void testAgentCreation() {
        System.out.println("\n[Test] Agent Creation");
        Agent agent = new Agent(99, "Will Smith", "will.smith@insurance.com", "Senior Agent");
        agent.displayUserInfo();
    }

    // Agent Viewing Customer Details
    private static void testAgentCustomerInteraction() {
        System.out.println("\n[Test] Agent Customer Interaction");
        Customer customer = new Customer(5, "Alice Smith", "alice.smith@example.com");
        Agent agent = new Agent(100, "Sarah Smith", "sarah.smith@insurance.com", "Junior Agent");
        agent.viewCustomerInfo(customer);
    }

    // Test: Home & Auto Policy Discounts ***NOT HANDLED CORRECTLY. NEEDS MORE THOUGHT WITH HOW PRICE IS HANDLED***
    private static void testHomeAutoDiscounts() {
        System.out.println("\n[Test] Home & Auto Policy Discounts");

        Customer customer = new Customer(10, "Ethan Smith", "ethan.smith@gmail.com");

        // Home policy
        HomeQuote homeQuote = new HomeQuote(1001, 0, 400000, 1.2, 1.1, 1.0);
        homeQuote.generateQuote(customer);
        double homeOriginalPrice = homeQuote.getQuotePrice(); // Store price before discount
        homeQuote.payForQuote();
        System.out.println("Final Home Quote Price (Stored in Policy): $" + homeQuote.getQuotePrice());
        Policy homePolicy = new Policy("H-1001", homeQuote, "Home", homeQuote.getPaymentDate());
        customer.addPolicy(customer, homePolicy);

        // Auto policy
        AutoQuote autoQuote = new AutoQuote(1002, 0, 30, 0, 5, 20000);
        autoQuote.generateQuote(customer);
        double autoOriginalPrice = autoQuote.getQuotePrice(); // Store price before discount
        autoQuote.payForQuote();
        System.out.println("Final Auto Quote Price (Stored in Policy): $" + autoQuote.getQuotePrice());
        Policy autoPolicy = new Policy("A-1002", autoQuote, "Auto", autoQuote.getPaymentDate());
        customer.addPolicy(customer, autoPolicy);

        System.out.println("\nExpected: 10% discount applied to both policies.");
        System.out.println("Comparison:");
        System.out.println("  Home Quote Before Discount: $" + homeOriginalPrice);
        System.out.println("  Home Quote After Discount: $" + homeQuote.getQuotePrice());
        System.out.println("  Auto Quote Before Discount: $" + autoOriginalPrice);
        System.out.println("  Auto Quote After Discount: $" + autoQuote.getQuotePrice());
        customer.displayUserInfo();
    }

    // Test: Multiple Auto Policies for a Customer
    private static void testMultipleAutoPolicies() {
        System.out.println("\n[Test] Multiple Auto Policies");

        Customer customer = new Customer(11, "Sophia Smith", "sophia.smith@gmail.com");

        AutoQuote firstCar = new AutoQuote(2001, 0, 28, 0, 3, 25000);
        firstCar.generateQuote(customer);
        firstCar.payForQuote();
        Policy firstCarPolicy = new Policy("A-2001", firstCar, "Auto", firstCar.getPaymentDate());
        customer.addPolicy(customer, firstCarPolicy);

        AutoQuote secondCar = new AutoQuote(2002, 0, 40, 1, 8, 18000);
        secondCar.generateQuote(customer);
        secondCar.payForQuote();
        Policy secondCarPolicy = new Policy("A-2002", secondCar, "Auto", secondCar.getPaymentDate());
        customer.addPolicy(customer, secondCarPolicy);

        System.out.println("Expected: Customer with two active auto policies.");
        customer.displayUserInfo();
    }

    // Exceeding Auto Policy Limit
    private static void testExceedingAutoPolicyLimits() {
        System.out.println("\n[Test] Exceeding Auto Policy Limits");
        Customer customer = new Customer(6, "Laura Smith", "laura.smith@gmail.com");
        try {
            for (int i = 1; i <= 4; i++) {
                AutoQuote autoQuote = new AutoQuote(500 + i, 0, 30, 0, 5, 25000);
                autoQuote.generateQuote(customer);
                autoQuote.payForQuote();
                Policy autoPolicy = new Policy("A-500" + i, autoQuote, "Auto", autoQuote.getPaymentDate());
                customer.addPolicy(customer, autoPolicy);
            }
        } catch (Exception e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }

    //Exceeding Home Policy Limit
    private static void testExceedingHomePolicyLimits() {
        System.out.println("\n[Test] Exceeding Home Policy Limits");
        Customer customer = new Customer(7, "Ian Somerton", "ian.somerton@gmail.com");
        try {
            for (int i = 1; i <= 3; i++) {
                HomeQuote homeQuote = new HomeQuote(1001, 0, 400000, 1.2, 1.1, 1.0);
                homeQuote.generateQuote(customer);
                homeQuote.payForQuote();
                Policy homePolicy = new Policy("A-1001", homeQuote, "Home", homeQuote.getPaymentDate());
                customer.addPolicy(customer, homePolicy);
            }
        } catch (Exception e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }

    // Test: Auto Quote Variations (Different Factors)
    private static void testAutoQuoteVariations() {
        System.out.println("\n[Test] Auto Quote Variations");

        Customer customer = new Customer(12, "Lucas Smith", "lucas.smith@gmail.com");

        // Case 1: Young driver with multiple accidents, expensive car
        AutoQuote riskyQuote = new AutoQuote(3001, 0, 22, 2, 2, 50000);
        riskyQuote.generateQuote(customer);
        System.out.println("Risky Driver Quote: " + riskyQuote);

        // Case 2: Older driver, no accidents, older car
        AutoQuote safeQuote = new AutoQuote(3002, 0, 50, 0, 15, 12000);
        safeQuote.generateQuote(customer);
        System.out.println("Safe Driver Quote: " + safeQuote);

        // Case 3: Middle-aged driver, 1 accident, mid-range car
        AutoQuote midRiskQuote = new AutoQuote(3003, 0, 35, 1, 7, 22000);
        midRiskQuote.generateQuote(customer);
        System.out.println("Mid-Risk Driver Quote: " + midRiskQuote);
    }

    // Test: Home Quote Variations (Different Home Values & Risk Factors)
    private static void testHomeQuoteVariations() {
        System.out.println("\n[Test] Home Quote Variations");

        Customer customer = new Customer(13, "Emma Smith", "emma.smith@gmail.com");

        // Case 1: Small home, low risk factors
        HomeQuote smallHome = new HomeQuote(4001, 0, 200000, 1.0, 1.0, 1.0);
        smallHome.generateQuote(customer);
        System.out.println("Small Home Quote: " + smallHome);

        // Case 2: Large home, moderate risk factors
        HomeQuote largeHome = new HomeQuote(4002, 0, 600000, 1.2, 1.2, 1.2);
        largeHome.generateQuote(customer);
        System.out.println("Large Home Quote: " + largeHome);

        // Case 3: High-value home, high-risk location
        HomeQuote highRiskHome = new HomeQuote(4003, 0, 1000000, 1.5, 1.5, 1.8);
        highRiskHome.generateQuote(customer);
        System.out.println("High-Risk Home Quote: " + highRiskHome);
    }



    // Policy Cancellation ***NEEDS MORE THOUGHT***
    private static void testPolicyCancellation() {
        System.out.println("\n[Test] Policy Cancellation");
        Customer customer = new Customer(7, "Daniel Smith", "daniel.smith@gmail.com");
        Quote autoQuote = new AutoQuote(601, 0, 35, 0, 2, 20000);
        autoQuote.generateQuote(customer);
        autoQuote.payForQuote();
        Policy autoPolicy = new Policy("A-601", autoQuote, "Auto", autoQuote.getPaymentDate());
        customer.addPolicy(customer, autoPolicy);
        autoPolicy.setStatus("Cancelled");
        customer.displayUserInfo();
    }

    // Invalid Cases & Edge Cases ***Invalid email handled. Need to implement checks for ID and Name***
    private static void testInvalidScenarios() {
        System.out.println("\n[Test] Invalid Scenarios");
        try {
            System.out.println("Attempting to create customer with invalid email...");
            new Customer(8, "Bob Builder", "invalid-email");
        } catch (Exception e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }
}
