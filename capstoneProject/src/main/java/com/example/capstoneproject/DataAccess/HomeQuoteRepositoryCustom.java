package com.example.capstoneproject.DataAccess;

import com.example.capstoneproject.Quotes.HomeQuote;
import com.example.capstoneproject.Users.Customer;

/**
 * Custom repository interface for managing HomeQuote-related operations.
 */
public interface HomeQuoteRepositoryCustom {

    /**
     * Saves a HomeQuote entity along with the associated Customer entity.
     *
     * @param homeQuote The HomeQuote to be saved.
     * @param customer The Customer associated with the quote.
     * @return The saved HomeQuote object.
     */
    HomeQuote saveWithQuote(HomeQuote homeQuote, Customer customer);
}
