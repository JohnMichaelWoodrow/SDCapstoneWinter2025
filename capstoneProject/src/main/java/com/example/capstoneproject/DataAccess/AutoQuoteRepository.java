package com.example.capstoneproject.DataAccess;

import com.example.capstoneproject.Quotes.AutoQuote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing AutoQuote entities.
 */
public interface AutoQuoteRepository extends JpaRepository<AutoQuote, Integer>, AutoQuoteRepositoryCustom {
}
