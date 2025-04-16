package com.example.capstoneproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.QuoteRepository;
import com.example.capstoneproject.Quotes.Quote;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing quote-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + QUOTES)
public class QuoteController {
    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * Retrieves all quotes from the database.
     *
     * @return A list of all quotes.
     */
    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    /**
     * Retrieves a quote by its unique ID.
     *
     * @param id The ID of the quote.
     * @return The corresponding Quote object.
     * @throws IllegalArgumentException if no quote is found with the given ID.
     */
    @GetMapping(ID)
    public Quote getQuoteById(@PathVariable int id) {
        return quoteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quote not found with ID: " + id));
    }

    /**
     * Creates a new quote and saves it to the database.
     *
     * @param quote The quote details.
     * @return The saved Quote object.
     */
    @PostMapping
    public Quote createQuote(@RequestBody Quote quote) {
        return quoteRepository.save(quote);
    }

    /**
     * Updates an existing quote's details.
     *
     * @param id The ID of the quote to update.
     * @param updatedQuote The updated quote details.
     * @return The updated Quote object.
     * @throws IllegalArgumentException if no quote is found with the given ID.
     */
    @PutMapping(ID)
    public Quote updateQuote(@PathVariable int id, @RequestBody Quote updatedQuote) {
        Quote existingQuote = quoteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quote not found with ID: " + id));
        existingQuote.setQuoteType(updatedQuote.getQuoteType());
        existingQuote.setQuotePrice(updatedQuote.getQuotePrice());
        existingQuote.setExpiryDate(updatedQuote.getExpiryDate());
        existingQuote.setPaid(updatedQuote.isPaid());
        return quoteRepository.save(existingQuote);
    }

    /**
     * Deletes a quote from the database.
     *
     * @param id The ID of the quote to delete.
     */
    @DeleteMapping(ID)
    public void deleteQuote(@PathVariable int id) {
        quoteRepository.deleteById(id);
    }
}
