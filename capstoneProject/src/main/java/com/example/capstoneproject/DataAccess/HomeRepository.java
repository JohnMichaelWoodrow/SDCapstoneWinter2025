package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstoneproject.Objects.Home;

/**
 * Repository interface for managing Home entities.
 * <p>
 * Provides built-in CRUD operations via JpaRepository.
 */
public interface HomeRepository extends JpaRepository<Home, Integer> {
}
