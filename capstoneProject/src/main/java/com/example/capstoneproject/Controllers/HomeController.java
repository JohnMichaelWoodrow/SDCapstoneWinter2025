package com.example.capstoneproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.HomeRepository;
import com.example.capstoneproject.Objects.Home;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing home-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + HOMES)
public class HomeController {
    @Autowired
    private HomeRepository homeRepository;

    /**
     * Retrieves all homes from the database.
     *
     * @return A list of all homes.
     */
    @GetMapping
    public List<Home> getAllHomes() {
        return homeRepository.findAll();
    }

    /**
     * Retrieves a home by its unique ID.
     *
     * @param id The ID of the home.
     * @return The corresponding Home object.
     * @throws IllegalArgumentException if no home is found with the given ID.
     */
    @GetMapping(ID)
    public Home getHomeById(@PathVariable int id) {
        return homeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Home not found with ID: " + id));
    }

    /**
     * Creates a new home and saves it to the database.
     *
     * @param home The home details.
     * @return The saved Home object.
     */
    @PostMapping
    public Home createHome(@RequestBody Home home) {
        return homeRepository.save(home);
    }

    /**
     * Updates an existing home's details.
     *
     * @param id The ID of the home to update.
     * @param updatedHome The updated home details.
     * @return The updated Home object.
     * @throws IllegalArgumentException if no home is found with the given ID.
     */
    @PutMapping(ID)
    public Home updateHome(@PathVariable int id, @RequestBody Home updatedHome) {
        Home existingHome = homeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Home not found with ID: " + id));
        existingHome.setAddress(updatedHome.getAddress());
        existingHome.setYearBuilt(updatedHome.getYearBuilt());
        existingHome.setHomeValue(updatedHome.getHomeValue());
        return homeRepository.save(existingHome);
    }

    /**
     * Deletes a home from the database.
     *
     * @param id The ID of the home to delete.
     */
    @DeleteMapping(ID)
    public void deleteHome(@PathVariable int id) {
        homeRepository.deleteById(id);
    }
}
