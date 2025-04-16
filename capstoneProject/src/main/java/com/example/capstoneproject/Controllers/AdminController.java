package com.example.capstoneproject.Controllers;

import com.example.capstoneproject.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.AdminRepository;
import com.example.capstoneproject.DataAccess.UserRepository;
import com.example.capstoneproject.Users.Admin;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for handling administrator-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + ADMINS)
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all administrators from the database.
     *
     * @return A list of all admins.
     */
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Retrieves an administrator by their ID.
     *
     * @param id The ID of the administrator.
     * @return The corresponding Admin object.
     * @throws IllegalArgumentException if no administrator is found with the given ID.
     */
    @GetMapping(ID)
    public Admin getAdminById(@PathVariable int id) {
        return adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + id));
    }

    /**
     * Creates a new administrator and saves it to the database.
     *
     * @param admin The administrator details.
     * @return The saved Admin object.
     */
    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminRepository.saveWithUser(admin, userRepository);
    }

    /**
     * Updates an existing administrator.
     *
     * @param id The ID of the administrator to update.
     * @param updatedAdmin The updated administrator details.
     * @return The updated Admin object.
     * @throws IllegalArgumentException if no administrator is found with the given ID.
     */
    @PutMapping(ID)
    public Admin updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + id));
        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin.setPassword(updatedAdmin.getPassword());
        return adminRepository.save(existingAdmin);
    }

    /**
     * Deletes an administrator and their associated user.
     *
     * @param id The ID of the administrator to delete.
     * @throws IllegalArgumentException if no administrator or user is found with the given ID.
     */
    @DeleteMapping(ID)
    public void deleteAdmin(@PathVariable int id) {
        User user = userRepository.findById(getAdminById(id).getUser().getId()).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        adminRepository.deleteById(id);
        userRepository.delete(user);
    }
}
