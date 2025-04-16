package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstoneproject.Policy.Policy;

/**
 * Repository interface for managing Policy entities.
 * <p>
 * Provides built-in CRUD operations via JpaRepository.
 */
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
