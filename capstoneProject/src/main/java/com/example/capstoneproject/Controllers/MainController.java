//package com.example.capstoneproject.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import com.example.capstoneproject.DataAccess.*;
//import com.example.capstoneproject.Objects.*;
//import com.example.capstoneproject.Policy.*;
//import com.example.capstoneproject.Quotes.*;
//import com.example.capstoneproject.Users.*;
//
//import java.util.Optional;
//
///**
// * MainController consolidates all CRUD operations for the application.
// */
//@Controller
//@RequestMapping(path = RESTNouns.VERSION_1)
//public class MainController {
//
//    // Repositories
//    @Autowired private AdminRepository adminRepository;
//    @Autowired private AgentRepository agentRepository;
//    @Autowired private CustomerRepository customerRepository;
//    @Autowired private HomeRepository homeRepository;
//    @Autowired private PolicyRepository policyRepository;
//    @Autowired private QuoteRepository quoteRepository;
//    @Autowired private UserRepository userRepository;
//    @Autowired private VehicleRepository vehicleRepository;
//
//    // ===== Admin CRUD =====
//    @PostMapping(path = RESTNouns.ADMINS)
//    public @ResponseBody Admin createAdmin(@RequestBody Admin admin) {
//        return adminRepository.save(admin);
//    }
//
//    @GetMapping(path = RESTNouns.ADMINS)
//    public @ResponseBody Iterable<Admin> getAllAdmins() {
//        return adminRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.ADMINS + RESTNouns.ID)
//    public @ResponseBody Optional<Admin> getAdminById(@PathVariable int id) {
//        return adminRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.ADMINS + RESTNouns.ID)
//    public @ResponseBody Admin updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
//        return adminRepository.findById(id)
//                .map(admin -> {
//                    admin.setName(updatedAdmin.getName());
//                    admin.setEmail(updatedAdmin.getEmail());
//                    admin.setRole(updatedAdmin.getRole());
//                    return adminRepository.save(admin);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Admin not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.ADMINS + RESTNouns.ID)
//    public @ResponseBody String deleteAdmin(@PathVariable int id) {
//        if (adminRepository.existsById(id)) {
//            adminRepository.deleteById(id);
//            return "Admin with ID " + id + " deleted successfully.";
//        } else {
//            return "Admin with ID " + id + " not found.";
//        }
//    }
//
//    // ===== Agent CRUD =====
//    @PostMapping(path = RESTNouns.AGENTS)
//    public @ResponseBody Agent createAgent(@RequestBody Agent agent) {
//        return agentRepository.save(agent);
//    }
//
//    @GetMapping(path = RESTNouns.AGENTS)
//    public @ResponseBody Iterable<Agent> getAllAgents() {
//        return agentRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.AGENTS + RESTNouns.ID)
//    public @ResponseBody Optional<Agent> getAgentById(@PathVariable int id) {
//        return agentRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.AGENTS + RESTNouns.ID)
//    public @ResponseBody Agent updateAgent(@PathVariable int id, @RequestBody Agent updatedAgent) {
//        return agentRepository.findById(id)
//                .map(agent -> {
//                    agent.setName(updatedAgent.getName());
//                    agent.setEmail(updatedAgent.getEmail());
//                    agent.setRole(updatedAgent.getRole());
//                    return agentRepository.save(agent);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Agent not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.AGENTS + RESTNouns.ID)
//    public @ResponseBody String deleteAgent(@PathVariable int id) {
//        if (agentRepository.existsById(id)) {
//            agentRepository.deleteById(id);
//            return "Agent with ID " + id + " deleted successfully.";
//        } else {
//            return "Agent with ID " + id + " not found.";
//        }
//    }
//
//    // ===== Customer CRUD =====
//    @PostMapping(path = RESTNouns.CUSTOMERS)
//    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//    @GetMapping(path = RESTNouns.CUSTOMERS)
//    public @ResponseBody Iterable<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.CUSTOMERS + RESTNouns.ID)
//    public @ResponseBody Optional<Customer> getCustomerById(@PathVariable int id) {
//        return customerRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.CUSTOMERS + RESTNouns.ID)
//    public @ResponseBody Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
//        return customerRepository.findById(id)
//                .map(customer -> {
//                    customer.setName(updatedCustomer.getName());
//                    customer.setEmail(updatedCustomer.getEmail());
//                    return customerRepository.save(customer);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.CUSTOMERS + RESTNouns.ID)
//    public @ResponseBody String deleteCustomer(@PathVariable int id) {
//        if (customerRepository.existsById(id)) {
//            customerRepository.deleteById(id);
//            return "Customer with ID " + id + " deleted successfully.";
//        } else {
//            return "Customer with ID " + id + " not found.";
//        }
//    }
//
//    // ===== Home CRUD =====
//    @PostMapping(path = RESTNouns.HOMES)
//    public @ResponseBody Home createHome(@RequestBody Home home) {
//        return homeRepository.save(home);
//    }
//
//    @GetMapping(path = RESTNouns.HOMES)
//    public @ResponseBody Iterable<Home> getAllHomes() {
//        return homeRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.HOMES + RESTNouns.ID)
//    public @ResponseBody Optional<Home> getHomeById(@PathVariable int id) {
//        return homeRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.HOMES + RESTNouns.ID)
//    public @ResponseBody Home updateHome(@PathVariable int id, @RequestBody Home updatedHome) {
//        return homeRepository.findById(id)
//                .map(home -> {
//                    home.setAddress(updatedHome.getAddress());
//                    home.setYearBuilt(updatedHome.getYearBuilt());
//                    home.setHomeValue(updatedHome.getHomeValue());
//                    return homeRepository.save(home);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Home not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.HOMES + RESTNouns.ID)
//    public @ResponseBody String deleteHome(@PathVariable int id) {
//        if (homeRepository.existsById(id)) {
//            homeRepository.deleteById(id);
//            return "Home with ID " + id + " deleted successfully.";
//        } else {
//            return "Home with ID " + id + " not found.";
//        }
//    }
//
//    // ===== Policy CRUD =====
//    @PostMapping(path = RESTNouns.POLICIES)
//    public @ResponseBody Policy createPolicy(@RequestBody Policy policy) {
//        Customer customer = customerRepository.findById(policy.getCustomer().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + policy.getCustomer().getId()));
//
//        Quote quote = quoteRepository.findById(policy.getQuoteId())
//                .orElseThrow(() -> new IllegalArgumentException("Quote not found with ID: " + policy.getQuoteId()));
//
//        // Use PolicyFactory to create the policy
//        Policy newPolicy = PolicyFactory.createPolicy(customer, quote);
//
//        // Save the policy to the database
//        return policyRepository.save(newPolicy);
//    }
//
//    @GetMapping(path = RESTNouns.POLICIES)
//    public @ResponseBody Iterable<Policy> getAllPolicies() {
//        return policyRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.POLICIES + RESTNouns.ID)
//    public @ResponseBody Optional<Policy> getPolicyById(@PathVariable int id) {
//        return policyRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.POLICIES + RESTNouns.ID)
//    public @ResponseBody Policy updatePolicy(@PathVariable int id, @RequestBody Policy updatedPolicy) {
//        return policyRepository.findById(id)
//                .map(policy -> getPolicy(updatedPolicy, policy, policyRepository))
//                .orElseThrow(() -> new IllegalArgumentException("Policy not found with ID: " + id));
//    }
//
//    static Policy getPolicy(@RequestBody Policy updatedPolicy, Policy policy, PolicyRepository policyRepository) {
//        policy.setPolicyNumber(updatedPolicy.getPolicyNumber());
//        policy.setPolicyType(updatedPolicy.getPolicyType());
//        policy.setStartDate(updatedPolicy.getStartDate());
//        policy.setEndDate(updatedPolicy.getEndDate());
//        policy.setStatus(updatedPolicy.getStatus());
//        policy.setPaid(updatedPolicy.isPaid());
//        return policyRepository.save(policy);
//    }
//
//    @DeleteMapping(path = RESTNouns.POLICIES + RESTNouns.ID)
//    public @ResponseBody String deletePolicy(@PathVariable int id) {
//        if (policyRepository.existsById(id)) {
//            policyRepository.deleteById(id);
//            return "Policy with ID " + id + " deleted successfully.";
//        } else {
//            return "Policy with ID " + id + " not found.";
//        }
//    }
//
//    // ===== Quote CRUD =====
//    @PostMapping(path = RESTNouns.QUOTES)
//    public @ResponseBody Quote createQuote(@RequestBody Quote quote) {
//        return quoteRepository.save(quote);
//    }
//
//    @GetMapping(path = RESTNouns.QUOTES)
//    public @ResponseBody Iterable<Quote> getAllQuotes() {
//        return quoteRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.QUOTES + RESTNouns.ID)
//    public @ResponseBody Optional<Quote> getQuoteById(@PathVariable int id) {
//        return quoteRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.QUOTES + RESTNouns.ID)
//    public @ResponseBody Quote updateQuote(@PathVariable int id, @RequestBody Quote updatedQuote) {
//        return quoteRepository.findById(id)
//                .map(quote -> {
//                    quote.setQuoteType(updatedQuote.getQuoteType());
//                    quote.setQuotePrice(updatedQuote.getQuotePrice());
//                    quote.setExpiryDate(updatedQuote.getExpiryDate());
//                    quote.setPaid(updatedQuote.isPaid());
//                    return quoteRepository.save(quote);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Quote not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.QUOTES + RESTNouns.ID)
//    public @ResponseBody String deleteQuote(@PathVariable int id) {
//        if (quoteRepository.existsById(id)) {
//            quoteRepository.deleteById(id);
//            return "Quote with ID " + id + " deleted successfully.";
//        } else {
//            return "Quote with ID " + id + " not found.";
//        }
//    }
//
//    // ===== User CRUD =====
//    @PostMapping(path = RESTNouns.USERS)
//    public @ResponseBody User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//    @GetMapping(path = RESTNouns.USERS)
//    public @ResponseBody Iterable<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.USERS + RESTNouns.ID)
//    public @ResponseBody Optional<User> getUserById(@PathVariable int id) {
//        return userRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.USERS + RESTNouns.ID)
//    public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setName(updatedUser.getName());
//                    user.setEmail(updatedUser.getEmail());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.USERS + RESTNouns.ID)
//    public @ResponseBody String deleteUser(@PathVariable int id) {
//        if (userRepository.existsById(id)) {
//            userRepository.deleteById(id);
//            return "User with ID " + id + " deleted successfully.";
//        } else {
//            return "User with ID " + id + " not found.";
//        }
//    }
//
//    // ===== Vehicle CRUD =====
//    @PostMapping(path = RESTNouns.VEHICLES)
//    public @ResponseBody Vehicle createVehicle(@RequestBody Vehicle vehicle) {
//        return vehicleRepository.save(vehicle);
//    }
//
//    @GetMapping(path = RESTNouns.VEHICLES)
//    public @ResponseBody Iterable<Vehicle> getAllVehicles() {
//        return vehicleRepository.findAll();
//    }
//
//    @GetMapping(path = RESTNouns.VEHICLES + RESTNouns.ID)
//    public @ResponseBody Optional<Vehicle> getVehicleById(@PathVariable int id) {
//        return vehicleRepository.findById(id);
//    }
//
//    @PutMapping(path = RESTNouns.VEHICLES + RESTNouns.ID)
//    public @ResponseBody Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle updatedVehicle) {
//        return vehicleRepository.findById(id)
//                .map(vehicle -> {
//                    vehicle.setMake(updatedVehicle.getMake());
//                    vehicle.setModel(updatedVehicle.getModel());
//                    vehicle.setYear(updatedVehicle.getYear());
//                    vehicle.setVin(updatedVehicle.getVin());
//                    return vehicleRepository.save(vehicle);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + id));
//    }
//
//    @DeleteMapping(path = RESTNouns.VEHICLES + RESTNouns.ID)
//    public @ResponseBody String deleteVehicle(@PathVariable int id) {
//        if (vehicleRepository.existsById(id)) {
//            vehicleRepository.deleteById(id);
//            return "Vehicle with ID " + id + " deleted successfully.";
//        } else {
//            return "Vehicle with ID " + id + " not found.";
//        }
//    }
//}
