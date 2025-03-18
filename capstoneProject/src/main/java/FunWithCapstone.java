import objects.Home;
import objects.Vehicle;
import users.*;
import policy.*;
import quotes.*;
import java.time.LocalDate;


// TODO: Class not setup for all recent changes


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
        testInvalidScenarios();

        System.out.println("\n===== TESTS COMPLETED =====");
    }

    // Objects.Home Quote Generation
    private static void testHomeQuoteCreation() {
        System.out.println("\n[Test] Objects.Home Quote Creation");
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

    // Paying for a Quote and Converting to a Policy (Updated)
    private static void testQuotePaymentAndPolicyCreation() {
        System.out.println("\n[Test] Quote Payment & Policy Creation");
        Customer customer = new Customer(3, "Michael Johnson", "michael.johnson@gmail.com");
        Home home = new Home("456 Oak St", 2000, 350000);
        Quote homeQuote = QuoteFactory.createHomeQuote(201, home, 0);

        System.out.println("Generated: " + homeQuote);
        customer.makePayment(LocalDate.now());

        Policy homePolicy = new Policy("H-001", homeQuote, "Home");
        customer.addPolicy(homePolicy);
        customer.displayUserInfo();
    }

    // Handling Expired Quotes
    private static void testQuoteExpirationHandling() {
        System.out.println("\n[Test] Quote Expiration Handling");
        Home home = new Home("789 Pine St", 2010, 250000);
        Quote expiredQuote = QuoteFactory.createHomeQuote(202, home, 0);
        expiredQuote.setExpiryDate(LocalDate.now().minusDays(31));
        System.out.println("Expired Quote: Expiry Date - " + expiredQuote.getExpiryDate());
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

    // Home & Auto Policy Discounts
    private static void testHomeAutoDiscounts() {
        System.out.println("\n[Test] Home & Auto Policy Discounts");

        Customer customer = new Customer(10, "Ethan Smith", "ethan.smith@gmail.com");
        Home home = new Home("123 Elm St", 2005, 400000);
        Vehicle vehicle = new Vehicle("Honda", "Civic", 2018, "VIN987654");

        Quote homeQuote = QuoteFactory.createHomeQuote(1001, home, 0);
        Quote autoQuote = QuoteFactory.createAutoQuote(1002, vehicle, 0);

        System.out.println("Generated Home Quote: " + homeQuote);
        System.out.println("Generated Auto Quote: " + autoQuote);

        customer.makePayment(LocalDate.now());

        Policy homePolicy = new Policy("H-1001", homeQuote, "Home");
        Policy autoPolicy = new Policy("A-1002", autoQuote, "Auto");

        customer.addPolicy(homePolicy);
        customer.addPolicy(autoPolicy);
        customer.displayUserInfo();
    }

    // Multiple Auto Policies for a Customer
    private static void testMultipleAutoPolicies() {
        System.out.println("\n[Test] Multiple Auto Policies");

        Customer customer = new Customer(11, "Sophia Smith", "sophia.smith@gmail.com");

        Vehicle firstCar = new Vehicle("Ford", "Focus", 2017, "VIN1234");
        Vehicle secondCar = new Vehicle("Chevrolet", "Malibu", 2015, "VIN5678");

        Quote firstCarQuote = QuoteFactory.createAutoQuote(2001, firstCar, 0);
        Quote secondCarQuote = QuoteFactory.createAutoQuote(2002, secondCar, 0);

        System.out.println("Generated Auto Quotes:");
        System.out.println("  - First Car: " + firstCarQuote);
        System.out.println("  - Second Car: " + secondCarQuote);

        // Process payment once before adding policies
        customer.makePayment(LocalDate.now());

        Policy firstCarPolicy = new Policy("A-2001", firstCarQuote, "Auto");
        Policy secondCarPolicy = new Policy("A-2002", secondCarQuote, "Auto");

        customer.addPolicy(firstCarPolicy);
        customer.addPolicy(secondCarPolicy);

        System.out.println("Expected: Customer with two active auto policies.");
        customer.displayUserInfo();
    }


    // Exceeding Auto Policy Limit
    private static void testExceedingAutoPolicyLimits() {
        System.out.println("\n[Test] Exceeding Auto Policy Limits");
        Customer customer = new Customer(6, "Laura Smith", "laura.smith@gmail.com");

        try {
            for (int i = 1; i <= 3; i++) {
                Vehicle vehicle = new Vehicle("Ford", "Fusion", 2012 + i, "VIN500" + i);
                Quote autoQuote = QuoteFactory.createAutoQuote(500 + i, vehicle, 0);
                customer.makePayment(LocalDate.now());
                Policy autoPolicy = new Policy("A-500" + i, autoQuote, "Auto");
                customer.addPolicy(autoPolicy);
            }
        } catch (Exception e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }

    // Exceeding Home Policy Limit
    private static void testExceedingHomePolicyLimits() {
        System.out.println("\n[Test] Exceeding Home Policy Limits");
        Customer customer = new Customer(7, "Ian Somerton", "ian.somerton@gmail.com");

        try {
            for (int i = 1; i <= 2; i++) { // Only 1 home policy allowed
                Home home = new Home("789 Maple St", 1990 + i, 500000);
                Quote homeQuote = QuoteFactory.createHomeQuote(1001, home, 0);
                customer.makePayment(LocalDate.now());
                Policy homePolicy = new Policy("H-1001", homeQuote, "Home");
                customer.addPolicy(homePolicy);
            }
        } catch (Exception e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }

    // Test: Auto Quote Variations
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

    // Test: Objects.Home Quote Variations
    private static void testHomeQuoteVariations() {
        System.out.println("\n[Test] Objects.Home Quote Variations");

        Customer customer = new Customer(13, "Emma Smith", "emma.smith@gmail.com");

        // Case 1: Small home, low risk factors
        HomeQuote smallHome = new HomeQuote(4001, 0, 200000, 1.0, 1.0, 1.0);
        smallHome.generateQuote(customer);
        System.out.println("Small Objects.Home Quote: " + smallHome);

        // Case 2: Large home, moderate risk factors
        HomeQuote largeHome = new HomeQuote(4002, 0, 600000, 1.2, 1.2, 1.2);
        largeHome.generateQuote(customer);
        System.out.println("Large Objects.Home Quote: " + largeHome);

        // Case 3: High-value home, high-risk location
        HomeQuote highRiskHome = new HomeQuote(4003, 0, 1000000, 1.5, 1.5, 1.8);
        highRiskHome.generateQuote(customer);
        System.out.println("High-Risk Objects.Home Quote: " + highRiskHome);
    }

    // Invalid Cases & Edge Cases
    private static void testInvalidScenarios() {
        System.out.println("\n[Test] Invalid Email");
        try {
            System.out.println("Attempting to create customer with invalid email...");
            new Customer(8, "Bob Builder", "invalid-email");
        } catch (Exception e) {
            System.out.println("Expected Error: " + e.getMessage());
        }
    }
}
