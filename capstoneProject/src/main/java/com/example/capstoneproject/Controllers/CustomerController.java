package com.example.capstoneproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.CustomerRepository;
import com.example.capstoneproject.DataAccess.UserRepository;
import com.example.capstoneproject.Users.Customer;
import com.example.capstoneproject.Users.User;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing customer-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + CUSTOMERS)
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers.
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param id The ID of the customer.
     * @return The corresponding Customer object.
     * @throws IllegalArgumentException if no customer is found with the given ID.
     */
    @GetMapping(ID)
    public Customer getCustomerById(@PathVariable int id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
    }

    /**
     * Creates a new customer and saves it to the database.
     *
     * @param customer The customer details.
     * @return The saved Customer object.
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.saveWithUser(customer, userRepository);
    }

    /**
     * Updates an existing customer's details.
     *
     * @param id The ID of the customer to update.
     * @param updatedCustomer The updated customer details.
     * @return The updated Customer object.
     * @throws IllegalArgumentException if no customer is found with the given ID.
     */
    @PutMapping(ID)
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPassword(updatedCustomer.getPassword());
        return customerRepository.save(existingCustomer);
    }

    /**
     * Deletes a customer and their associated user.
     *
     * @param id The ID of the customer to delete.
     * @throws IllegalArgumentException if no customer or user is found with the given ID.
     */
    @DeleteMapping(ID)
    public void deleteCustomer(@PathVariable int id) {
        User user = userRepository.findById(getCustomerById(id).getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
        customerRepository.deleteById(id);
        userRepository.delete(user);
    }
}
