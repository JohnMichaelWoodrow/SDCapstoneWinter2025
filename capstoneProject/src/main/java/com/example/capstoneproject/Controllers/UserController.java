package com.example.capstoneproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.UserRepository;
import com.example.capstoneproject.Users.User;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + USERS)
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id The ID of the user.
     * @return The corresponding User object.
     * @throws IllegalArgumentException if no user is found with the given ID.
     */
    @GetMapping(ID)
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    /**
     * Creates a new user and saves it to the database.
     *
     * @param user The user details.
     * @return The saved User object.
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setRole(user.getRole().toLowerCase()); // Ensure role is stored consistently
        return userRepository.save(user);
    }

    /**
     * Updates an existing user's details.
     *
     * @param id The ID of the user to update.
     * @param updatedUser The updated user details.
     * @return The updated User object.
     * @throws IllegalArgumentException if no user is found with the given ID.
     */
    @PutMapping(ID)
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        existingUser.setRole(updatedUser.getRole().toLowerCase()); // Update role field
        return userRepository.save(existingUser);
    }

    /**
     * Deletes a user from the database.
     *
     * @param id The ID of the user to delete.
     */
    @DeleteMapping(ID)
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
