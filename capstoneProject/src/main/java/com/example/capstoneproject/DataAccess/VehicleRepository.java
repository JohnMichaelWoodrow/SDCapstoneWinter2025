package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstoneproject.Objects.Vehicle;

/**
 * Repository interface for managing Vehicle entities.
 * <p>
 * Provides built-in CRUD operations via JpaRepository.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
