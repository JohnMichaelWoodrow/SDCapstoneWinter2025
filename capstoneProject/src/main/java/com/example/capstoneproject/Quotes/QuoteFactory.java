package com.example.capstoneproject.Quotes;

import com.example.capstoneproject.Objects.Home;
import com.example.capstoneproject.Objects.Vehicle;
import com.example.capstoneproject.Users.Customer;

/**
 * Factory class for creating insurance quotes based on customer details.
 */
public class QuoteFactory {

    /**
     * Creates a HomeQuote instance based on the customer's home details.
     * Calculates risk factors influencing the final quote price.
     *
     * @param customer The customer applying for the home quote.
     * @param home The home associated with the quote.
     * @param baseRate The base rate used to calculate the quote price.
     * @return The generated HomeQuote object.
     */
    public static HomeQuote createHomeQuote(Customer customer, Home home, double baseRate) {
        double homeValueFactor = (home.getHomeValue() > 250000) ? 1 + (home.getHomeValue() - 250000) * 0.002 : 1.0;
        double liabilityFactor = home.getLiabilityLimit() == 2_000_000 ? 1.25 : 1.0;
        double ageFactor = home.getYearBuilt() < java.time.LocalDate.now().getYear() - 50 ? 1.5 :
                home.getYearBuilt() < java.time.LocalDate.now().getYear() - 25 ? 1.25 : 1.0;
        double heatingFactor = home.getHeatingType().equalsIgnoreCase("oil") ? 2.0 :
                home.getHeatingType().equalsIgnoreCase("wood") ? 1.25 : 1.0;
        double locationFactor = home.getLocation().equalsIgnoreCase("rural") ? 1.15 : 1.0;
        double discountFactor = customer.getPolicies().stream()
                .anyMatch(policy -> policy.getPolicyType().equals("Auto") && policy.getStatus().equals("Active"))
                ? 0.9 : 1.0;

        double riskFactor = homeValueFactor * liabilityFactor * ageFactor * heatingFactor * locationFactor * discountFactor;
        return new HomeQuote(customer.getQuotes().size() + 1, home, baseRate, riskFactor);
    }

    /**
     * Creates an AutoQuote instance based on the customer's vehicle details.
     * Applies discount factors if applicable.
     *
     * @param customer The customer applying for the auto quote.
     * @param vehicle The vehicle associated with the quote.
     * @param driverAge The age of the driver.
     * @param accidentCount The number of accidents the driver has had.
     * @param baseRate The base rate used to calculate the quote price.
     * @return The generated AutoQuote object.
     */
    public static AutoQuote createAutoQuote(Customer customer, Vehicle vehicle, int driverAge, int accidentCount, double baseRate) {
        // Compute discount factor based on whether the customer has an active Home policy.
        double discountFactor = customer.getPolicies().stream()
                .anyMatch(policy -> policy.getPolicyType().equals("Home") && policy.getStatus().equals("Active"))
                ? 0.9 : 1.0;

        // Let AutoQuote handle the detailed risk factor calculations.
        double premium = AutoQuote.calculatePremium(baseRate, driverAge, accidentCount, vehicle, discountFactor);
        return new AutoQuote(customer.getQuotes().size() + 1, vehicle, driverAge, accidentCount, premium);
    }
}
