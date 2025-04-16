package com.example.capstoneproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.VehicleRepository;
import com.example.capstoneproject.Objects.Vehicle;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing vehicle-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + VEHICLES)
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * Retrieves all vehicles from the database.
     *
     * @return A list of all vehicles.
     */
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /**
     * Retrieves a vehicle by its unique ID.
     *
     * @param id The ID of the vehicle.
     * @return The corresponding Vehicle object.
     * @throws IllegalArgumentException if no vehicle is found with the given ID.
     */
    @GetMapping(ID)
    public Vehicle getVehicleById(@PathVariable int id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + id));
    }

    /**
     * Creates a new vehicle and saves it to the database.
     *
     * @param vehicle The vehicle details.
     * @return The saved Vehicle object.
     */
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /**
     * Updates an existing vehicle's details.
     *
     * @param id The ID of the vehicle to update.
     * @param updatedVehicle The updated vehicle details.
     * @return The updated Vehicle object.
     * @throws IllegalArgumentException if no vehicle is found with the given ID.
     */
    @PutMapping(ID)
    public Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle updatedVehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + id));
        existingVehicle.setMake(updatedVehicle.getMake());
        existingVehicle.setModel(updatedVehicle.getModel());
        existingVehicle.setYear(updatedVehicle.getYear());
        existingVehicle.setVin(updatedVehicle.getVin());
        return vehicleRepository.save(existingVehicle);
    }

    /**
     * Deletes a vehicle from the database.
     *
     * @param id The ID of the vehicle to delete.
     */
    @DeleteMapping(ID)
    public void deleteVehicle(@PathVariable int id) {
        vehicleRepository.deleteById(id);
    }
}
