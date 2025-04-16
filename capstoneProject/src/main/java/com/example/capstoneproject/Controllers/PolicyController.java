package com.example.capstoneproject.Controllers;

import com.example.capstoneproject.DataAccess.CustomerRepository;
import com.example.capstoneproject.DataAccess.PolicyRepository;
import com.example.capstoneproject.DataAccess.QuoteRepository;
import com.example.capstoneproject.Policy.Policy;
import com.example.capstoneproject.Policy.PolicyFactory;
import com.example.capstoneproject.Requests.PolicyRequest;
import com.example.capstoneproject.Quotes.Quote;
import com.example.capstoneproject.Users.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing insurance policies.
 */
@RestController
@RequestMapping(VERSION_1 + POLICIES)
public class PolicyController {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * Retrieves all insurance policies from the database.
     *
     * @return A list of all policies.
     */
    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    /**
     * Retrieves an insurance policy by its unique ID.
     *
     * @param id The ID of the policy.
     * @return The corresponding Policy object.
     * @throws IllegalArgumentException if no policy is found with the given ID.
     */
    @GetMapping(ID)
    public Policy getPolicyById(@PathVariable int id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Policy not found with ID: " + id));
    }

    /**
     * Creates a new insurance policy using customer and quote details.
     *
     * @param request The request object containing customer and quote IDs.
     * @return The created Policy object.
     * @throws IllegalArgumentException if the customer or quote is not found.
     */
    @PostMapping
    public Policy createPolicy(@RequestBody PolicyRequest request) {
        // Fetch the customer based on the ID
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + request.getCustomerId()));

        // Fetch the quote based on the ID
        Quote quote = quoteRepository.findById(request.getQuoteId())
                .orElseThrow(() -> new IllegalArgumentException("Quote not found with ID: " + request.getQuoteId()));

        // Use PolicyFactory to create the policy, policy number will be generated automatically
        Policy policy = PolicyFactory.createPolicy(customer, quote);

        // Save the newly created policy to the repository
        return policyRepository.save(policy);
    }

    /**
     * Updates an existing insurance policy.
     *
     * @param id The ID of the policy to update.
     * @param updatedPolicy The updated policy details.
     * @return The updated Policy object.
     * @throws IllegalArgumentException if no policy is found with the given ID.
     */
    @PutMapping(ID)
    public Policy updatePolicy(@PathVariable int id, @RequestBody Policy updatedPolicy) {
        // Fetch the existing policy to update
        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Policy not found with ID: " + id));

        // Update necessary fields
        existingPolicy.setStatus(updatedPolicy.getStatus());
        existingPolicy.setPaid(updatedPolicy.isPaid());

        // Recalculate total premium if base premium or tax rate were updated
        existingPolicy.setBasePremium(updatedPolicy.getBasePremium());
        existingPolicy.setTaxRate(updatedPolicy.getTaxRate());

        // Save the updated policy
        return policyRepository.save(existingPolicy);
    }

    /**
     * Deletes an insurance policy from the database.
     *
     * @param id The ID of the policy to delete.
     */
    @DeleteMapping(ID)
    public void deletePolicy(@PathVariable int id) {
        // Delete the policy by ID
        policyRepository.deleteById(id);
    }
}
