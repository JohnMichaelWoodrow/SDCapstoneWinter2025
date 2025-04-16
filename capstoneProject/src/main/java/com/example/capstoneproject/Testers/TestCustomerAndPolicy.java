//package com.example.capstoneproject.Testers;
//
//import com.example.capstoneproject.Users.Customer;
//import com.example.capstoneproject.Policy.Policy;
//import com.example.capstoneproject.Policy.PolicyFactory;
//import com.example.capstoneproject.Quotes.Quote;
//
//public class TestCustomerAndPolicy {
//    public static void main(String[] args) {
//        // Create a customer
//        Customer customer = new Customer(1, "John Doe", "john.doe@example.com");
//        System.out.println("Customer created: " + customer);
//
//        // Mock a quote
//        Quote quote = new Quote(1, "Auto", 1500) {
//            @Override
//            public String toString() {
//                return "Mock Quote: ID = " + getId() + ", Type = " + getQuoteType() + ", Price = $" + getQuotePrice();
//            }
//        };
//
//        // Create a policy from the quote using PolicyFactory
//        Policy policy = PolicyFactory.createPolicy(customer, quote);
//        System.out.println("Policy created: " + policy);
//
//        // Check customer's policies
//        System.out.println("Customer's Policies: " + customer.getPolicies());
//
//        // Test policy renewal
//        policy.renewPolicy();
//        System.out.println("Renewed Policy: " + policy);
//
//        // Test policy cancellation
//        policy.cancelPolicy();
//        System.out.println("Canceled Policy: " + policy);
//    }
//}
