package com.example.capstoneproject.Controllers;

import com.example.capstoneproject.DataAccess.AutoQuoteRepository;
import com.example.capstoneproject.DataAccess.CustomerRepository;
import com.example.capstoneproject.DataAccess.VehicleRepository;
import com.example.capstoneproject.Quotes.AutoQuote;
import com.example.capstoneproject.Quotes.QuoteFactory;
import com.example.capstoneproject.Requests.AutoQuoteRequest;
import com.example.capstoneproject.Users.Customer;
import com.example.capstoneproject.Objects.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing auto insurance quotes.
 */
@RestController
@RequestMapping(VERSION_1 + AUTOQUOTE)
public class AutoQuoteController {

    @Autowired
    private AutoQuoteRepository autoQuoteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * Retrieves all auto insurance quotes from the database.
     *
     * @return A list of all auto quotes.
     */
    @GetMapping
    public List<AutoQuote> getAllAutoQuotes() {
        return autoQuoteRepository.findAll();
    }

    /**
     * Creates a new auto insurance quote using customer and vehicle details.
     *
     * @param request The request object containing customer and vehicle details.
     * @return The created AutoQuote object.
     * @throws IllegalArgumentException if customer or vehicle is not found.
     */
    @PostMapping
    public AutoQuote createAutoQuote(@RequestBody AutoQuoteRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

        // Use the QuoteFactory to create an AutoQuote, with premium calculated internally
        AutoQuote autoQuote = QuoteFactory.createAutoQuote(
                customer,
                vehicle,
                request.getDriverAge(),
                request.getAccidentCount(),
                request.getBaseRate()
        );

        return autoQuoteRepository.saveWithQuote(autoQuote, customer);
    }

    /**
     * Retrieves an auto insurance quote by its ID.
     *
     * @param id The ID of the quote.
     * @return The corresponding AutoQuote object.
     * @throws IllegalArgumentException if the quote is not found.
     */
    @GetMapping("/{id}")
    public AutoQuote getAutoQuoteById(@PathVariable int id) {
        return autoQuoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AutoQuote not found"));
    }

    /**
     * Updates an existing auto insurance quote using a request object.
     *
     * @param id The ID of the quote to update.
     * @param request The request containing updated values.
     * @return The updated AutoQuote object.
     * @throws IllegalArgumentException if the quote, customer, or vehicle is not found.
     */
    @PutMapping("/{id}")
    public AutoQuote updateAutoQuote(@PathVariable int id, @RequestBody AutoQuoteRequest request) {
        AutoQuote existingQuote = autoQuoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AutoQuote not found with ID: " + id));

        if (request.getCustomerId() != 0) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + request.getCustomerId()));
            existingQuote.setCustomer(customer);
        }

        if (request.getVehicleId() != 0) {
            Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + request.getVehicleId()));
            existingQuote.setVehicle(vehicle);
        }

        // Update basic fields
        existingQuote.setDriverAge(request.getDriverAge());
        existingQuote.setAccidentCount(request.getAccidentCount());

        // Fetch customer and vehicle again for premium recalculation
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + request.getCustomerId()));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + request.getVehicleId()));

        // Recalculate premium using factory logic
        AutoQuote tempQuote = QuoteFactory.createAutoQuote(customer, vehicle, request.getDriverAge(), request.getAccidentCount(), request.getBaseRate());
        existingQuote.setQuotePrice(tempQuote.getQuotePrice());

        // Optional fields
        existingQuote.setPaid(request.isPaid());
        existingQuote.setExpiryDate(request.getExpiryDate());

        return autoQuoteRepository.save(existingQuote);
    }

    /**
     * Updates the payment status of an auto insurance quote.
     *
     * @param id The ID of the quote.
     * @param paid The updated payment status.
     * @return The updated AutoQuote object.
     * @throws IllegalArgumentException if the quote is not found.
     */
    @PutMapping("/{id}/paid")
    public AutoQuote updateAutoQuotePaidStatus(@PathVariable int id, @RequestParam boolean paid) {
        AutoQuote quote = autoQuoteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AutoQuote not found with ID: " + id));

        quote.setPaid(paid);
        return autoQuoteRepository.save(quote);
    }
}
