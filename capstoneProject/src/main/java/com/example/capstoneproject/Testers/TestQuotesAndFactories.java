//package com.example.capstoneproject.Testers;
//
//import com.example.capstoneproject.Objects.Home;
//import com.example.capstoneproject.Objects.Vehicle;
//import com.example.capstoneproject.Users.Customer;
//import com.example.capstoneproject.Quotes.QuoteFactory;
//import com.example.capstoneproject.Quotes.HomeQuote;
//import com.example.capstoneproject.Quotes.AutoQuote;
//
//public class TestQuotesAndFactories {
//    public static void main(String[] args) {
//        // Create a Customer
//        Customer customer = new Customer(1, "Alice Smith", "alice.smith@example.com");
//        System.out.println("Customer created: " + customer);
//
//        // Create a Home and HomeQuote
//        Home home = new Home(
//                "456 Elm St",
//                1995,
//                300000,
//                "Bungalow",
//                "Electric",
//                "Rural",
//                2_000_000
//        );
//        System.out.println("Home created: " + home);
//
//        HomeQuote homeQuote = QuoteFactory.createHomeQuote(customer, home, 500);
//        System.out.println("HomeQuote created: " + homeQuote);
//
//        // Create a Vehicle and AutoQuote
//        Vehicle vehicle = new Vehicle(
//                "Honda",
//                "Civic",
//                2010,
//                "VIN5678901234"
//        );
//        System.out.println("Vehicle created: " + vehicle);
//
//        AutoQuote autoQuote = QuoteFactory.createAutoQuote(customer, vehicle, 24, 1, 750);
//        System.out.println("AutoQuote created: " + autoQuote);
//
//        // Modify Customer's Policies and Verify Discounts
//        System.out.println("Final HomeQuote Premium: " + homeQuote.getQuotePrice());
//        System.out.println("Final AutoQuote Premium: " + autoQuote.getQuotePrice());
//    }
//}
