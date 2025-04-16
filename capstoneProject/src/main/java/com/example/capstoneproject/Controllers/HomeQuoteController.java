package com.example.capstoneproject.Controllers;

import com.example.capstoneproject.DataAccess.CustomerRepository;
import com.example.capstoneproject.DataAccess.HomeQuoteRepository;
import com.example.capstoneproject.DataAccess.HomeRepository;
import com.example.capstoneproject.Quotes.HomeQuote;
import com.example.capstoneproject.Quotes.QuoteFactory;
import com.example.capstoneproject.Requests.HomeQuoteRequest;
import com.example.capstoneproject.Users.Customer;
import com.example.capstoneproject.Objects.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing home insurance quotes.
 */
@RestController
@RequestMapping(VERSION_1 + HOMEQUOTE)
public class HomeQuoteController {

    @Autowired
    private HomeQuoteRepository homeQuoteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HomeRepository homeRepository;

    /**
     * Retrieves all home insurance quotes from the database.
     *
     * @return A list of all home quotes.
     */
    @GetMapping
    public List<HomeQuote> getAllHomeQuotes() {
        return homeQuoteRepository.findAll();
    }

    /**
     * Creates a new home insurance quote.
     *
     * @param request The request containing necessary details for generating a quote.
     * @return The generated HomeQuote object.
     * @throws IllegalArgumentException if the customer or home ID is missing or invalid.
     */
    @PostMapping
    public HomeQuote createHomeQuote(@RequestBody HomeQuoteRequest request) {
        // Print the HomeQuoteRequest object to validate the input
        System.out.println(request.toString());

        // Validate that customerId is not the default value (0)
        if (request.getCustomerId() == 0) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        // Validate that homeId is not the default value (0)
        if (request.getHomeId() == 0) {
            throw new IllegalArgumentException("Home ID is required.");
        }

        // Retrieve Customer and Home using the provided IDs
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + request.getCustomerId()));

        Home home = homeRepository.findById(request.getHomeId())
                .orElseThrow(() -> new IllegalArgumentException("Home not found with ID: " + request.getHomeId()));

        // Create the HomeQuote using the QuoteFactory
        HomeQuote homeQuote = QuoteFactory.createHomeQuote(customer, home, request.getBaseRate());

        // Save the HomeQuote using the custom saveWithQuote method
        return homeQuoteRepository.saveWithQuote(homeQuote, customer);
    }

    /**
     * Retrieves a home insurance quote by its unique ID.
     *
     * @param id The ID of the quote.
     * @return The corresponding HomeQuote object.
     * @throws IllegalArgumentException if no quote is found with the given ID.
     */
    @GetMapping("/{id}")
    public HomeQuote getHomeQuoteById(@PathVariable int id) {
        return homeQuoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HomeQuote not found"));
    }

    /**
     * Updates an existing home insurance quote.
     *
     * @param id The ID of the quote to update.
     * @param request The request containing updated values.
     * @return The updated HomeQuote object.
     * @throws IllegalArgumentException if the quote, customer, or home is not found.
     */
    @PutMapping("/{id}")
    public HomeQuote updateHomeQuote(@PathVariable int id, @RequestBody HomeQuoteRequest request) {
        HomeQuote existingQuote = homeQuoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HomeQuote not found with ID: " + id));

        // Optional: Validate updated customer and home
        if (request.getCustomerId() != 0) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + request.getCustomerId()));
            existingQuote.setCustomer(customer);
        }

        if (request.getHomeId() != 0) {
            Home home = homeRepository.findById(request.getHomeId())
                    .orElseThrow(() -> new IllegalArgumentException("Home not found with ID: " + request.getHomeId()));
            existingQuote.setHome(home);
        }

        // Update quote values
        existingQuote.setBaseRate(request.getBaseRate());
        existingQuote.setQuotePrice(HomeQuote.calculatePremium(request.getBaseRate(), existingQuote.getRiskFactor()));
        existingQuote.setPaid(request.isPaid());
        existingQuote.setExpiryDate(request.getExpiryDate());

        return homeQuoteRepository.save(existingQuote);
    }

    /**
     * Updates the payment status of a home insurance quote.
     *
     * @param id The ID of the quote.
     * @param paid The updated payment status.
     * @return The updated HomeQuote object.
     * @throws IllegalArgumentException if the quote is not found.
     */
    @PutMapping("/{id}/paid")
    public HomeQuote updateHomeQuotePaidStatus(@PathVariable int id, @RequestParam boolean paid) {
        HomeQuote quote = homeQuoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HomeQuote not found with ID: " + id));

        quote.setPaid(paid);
        return homeQuoteRepository.save(quote);
    }
}
