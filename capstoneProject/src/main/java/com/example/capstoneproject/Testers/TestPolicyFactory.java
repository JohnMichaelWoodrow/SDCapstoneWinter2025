//package com.example.capstoneproject.Testers;
//
//import com.example.capstoneproject.Policy.Policy;
//import com.example.capstoneproject.Policy.PolicyFactory;
//import com.example.capstoneproject.Users.Customer;
//import com.example.capstoneproject.Objects.Home;
//import com.example.capstoneproject.Quotes.QuoteFactory;
//import com.example.capstoneproject.Quotes.HomeQuote;
//
//public class TestPolicyFactory {
//    public static void main(String[] args) {
//        // Step 1: Create a Customer
//        Customer customer = new Customer(1, "John Doe", "john.doe@example.com");
//        System.out.println("Customer created: " + customer);
//
//        // Step 2: Create a Home object
//        Home home = new Home(
//                "123 Main St",
//                1980,
//                400000,
//                "Standalone",
//                "Oil",
//                "Urban",
//                1_000_000
//        );
//        System.out.println("Home created: " + home);
//
//        // Step 3: Create a HomeQuote using QuoteFactory
//        HomeQuote homeQuote = QuoteFactory.createHomeQuote(customer, home, 500);
//        System.out.println("HomeQuote created: " + homeQuote);
//
//        // Step 4: Create a Policy using PolicyFactory
//        Policy policy = PolicyFactory.createPolicy(customer, homeQuote);
//        System.out.println("Policy created: " + policy);
//
//        // Step 5: Verify restrictions - Attempt to create another home policy
//        try {
//            PolicyFactory.createPolicy(customer, homeQuote);
//        } catch (IllegalStateException e) {
//            System.err.println("Exception: " + e.getMessage());
//        }
//    }
//}
