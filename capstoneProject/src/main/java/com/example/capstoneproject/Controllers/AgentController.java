package com.example.capstoneproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.capstoneproject.DataAccess.AgentRepository;
import com.example.capstoneproject.DataAccess.UserRepository;
import com.example.capstoneproject.Users.Agent;
import com.example.capstoneproject.Users.User;

import java.util.List;

import static com.example.capstoneproject.Controllers.RESTNouns.*;

/**
 * Controller for managing agent-related operations.
 */
@RestController
@RequestMapping(VERSION_1 + AGENTS)
public class AgentController {
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all agents from the database.
     *
     * @return A list of all agents.
     */
    @GetMapping
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    /**
     * Retrieves an agent by their unique ID.
     *
     * @param id The ID of the agent.
     * @return The corresponding Agent object.
     * @throws IllegalArgumentException if no agent is found with the given ID.
     */
    @GetMapping(ID)
    public Agent getAgentById(@PathVariable int id) {
        return agentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agent not found with ID: " + id));
    }

    /**
     * Creates a new agent and saves it to the database.
     *
     * @param agent The agent details.
     * @return The saved Agent object.
     */
    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentRepository.saveWithUser(agent, userRepository);
    }

    /**
     * Updates an existing agent's details.
     *
     * @param id The ID of the agent to update.
     * @param updatedAgent The updated agent details.
     * @return The updated Agent object.
     * @throws IllegalArgumentException if no agent is found with the given ID.
     */
    @PutMapping(ID)
    public Agent updateAgent(@PathVariable int id, @RequestBody Agent updatedAgent) {
        Agent existingAgent = agentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agent not found with ID: " + id));
        existingAgent.setName(updatedAgent.getName());
        existingAgent.setEmail(updatedAgent.getEmail());
        existingAgent.setPassword(updatedAgent.getPassword());
        return agentRepository.save(existingAgent);
    }

    /**
     * Deletes an agent and their associated user.
     *
     * @param id The ID of the agent to delete.
     * @throws IllegalArgumentException if no agent or user is found with the given ID.
     */
    @DeleteMapping(ID)
    public void deleteAgent(@PathVariable int id) {
        User user = userRepository.findById(getAgentById(id).getUser().getId()).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        agentRepository.deleteById(id);
        userRepository.delete(user);
    }
}
