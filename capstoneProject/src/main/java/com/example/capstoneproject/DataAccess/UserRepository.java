package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstoneproject.Users.User;

/**
 * Repository interface for managing User entities.
 * <p>
 * Provides built-in CRUD operations via JpaRepository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
