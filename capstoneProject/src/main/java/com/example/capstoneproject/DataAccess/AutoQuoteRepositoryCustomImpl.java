package com.example.capstoneproject.DataAccess;

import com.example.capstoneproject.Quotes.AutoQuote;
import com.example.capstoneproject.Users.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Implementation of the custom repository for managing AutoQuote-related operations.
 */
@Repository
public class AutoQuoteRepositoryCustomImpl implements AutoQuoteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves an AutoQuote entity along with the associated Customer entity.
     *
     * @param autoQuote The AutoQuote to be saved.
     * @param customer The Customer associated with the quote.
     * @return The saved AutoQuote object.
     */
    @Override
    @Transactional
    public AutoQuote saveWithQuote(AutoQuote autoQuote, Customer customer) {
        // Set metadata
        autoQuote.setId(0);
        autoQuote.setQuoteType("Auto");
        autoQuote.setCustomer(customer);
        autoQuote.setExpiryDate(LocalDate.now().plusDays(30));
        autoQuote.setPaid(false);

        // Persist or merge depending on if it's new
        if (autoQuote.getId() <= 0) {
            entityManager.persist(autoQuote);
        } else {
            autoQuote = entityManager.merge(autoQuote);
        }

        return autoQuote;
    }
}
