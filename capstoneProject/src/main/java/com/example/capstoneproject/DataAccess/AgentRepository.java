package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.capstoneproject.Users.Agent;
import com.example.capstoneproject.Users.User;

/**
 * Repository interface for managing Agent entities.
 */
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    /**
     * Saves an Agent entity along with an associated User entity.
     * Ensures that the Agent is properly linked to a newly created User.
     *
     * @param agent The Agent entity to be saved.
     * @param userRepository The UserRepository used to persist the associated User.
     * @return The saved Agent entity.
     */
    @Transactional
    default Agent saveWithUser(Agent agent, UserRepository userRepository) {
        User user = new User();
        user.setRole("agent");
        User savedUser = userRepository.save(user);

        agent.setUser(savedUser);

        return save(agent);
    }
}
