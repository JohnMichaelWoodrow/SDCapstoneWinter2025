package com.example.capstoneproject.DataAccess;

import com.example.capstoneproject.Quotes.HomeQuote;
import com.example.capstoneproject.Users.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the custom repository for managing HomeQuote-related operations.
 */
@Repository
public class HomeQuoteRepositoryCustomImpl implements HomeQuoteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a HomeQuote entity along with the associated Customer entity.
     *
     * @param homeQuote The HomeQuote to be saved.
     * @param customer The Customer associated with the quote.
     * @return The saved HomeQuote object.
     */
    @Override
    @Transactional
    public HomeQuote saveWithQuote(HomeQuote homeQuote, Customer customer) {
        homeQuote.setId(0); // Force new ID
        homeQuote.setQuoteType("Home");
        homeQuote.setCustomer(customer);
        homeQuote.setExpiryDate(java.time.LocalDate.now().plusDays(30));
        homeQuote.setPaid(false);

        entityManager.persist(homeQuote);

        return homeQuote;
    }
}
