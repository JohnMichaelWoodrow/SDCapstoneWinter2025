package com.example.capstoneproject.DataAccess;

import com.example.capstoneproject.Quotes.AutoQuote;
import com.example.capstoneproject.Users.Customer;

/**
 * Custom repository interface for managing AutoQuote-related operations.
 */
public interface AutoQuoteRepositoryCustom {

    /**
     * Saves an AutoQuote entity along with the associated Customer entity.
     *
     * @param autoQuote The AutoQuote to be saved.
     * @param customer The Customer associated with the quote.
     * @return The saved AutoQuote object.
     */
    AutoQuote saveWithQuote(AutoQuote autoQuote, Customer customer);
}
