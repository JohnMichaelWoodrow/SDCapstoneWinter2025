package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstoneproject.Quotes.Quote;

/**
 * Repository interface for managing Quote entities.
 * <p>
 * Provides built-in CRUD operations via JpaRepository.
 */
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
