package com.example.capstoneproject.DataAccess;

import com.example.capstoneproject.Quotes.HomeQuote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing HomeQuote entities.
 * <p>
 * Extends JpaRepository to provide built-in CRUD operations.
 * Also extends HomeQuoteRepositoryCustom to add custom database operations.
 */
public interface HomeQuoteRepository extends JpaRepository<HomeQuote, Integer>, HomeQuoteRepositoryCustom {
    // JpaRepository provides basic operations such as save(), findById(), etc.
    // HomeQuoteRepositoryCustom provides custom operations like saveWithQuote()
}
