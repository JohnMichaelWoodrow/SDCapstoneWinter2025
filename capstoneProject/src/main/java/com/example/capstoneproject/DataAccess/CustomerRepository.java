package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.capstoneproject.Users.Customer;
import com.example.capstoneproject.Users.User;

/**
 * Repository interface for managing Customer entities.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Saves a Customer entity along with an associated User entity.
     * Ensures that the Customer is properly linked to a newly created User.
     *
     * @param customer The Customer entity to be saved.
     * @param userRepository The UserRepository used to persist the associated User.
     * @return The saved Customer entity.
     */
    @Transactional
    default Customer saveWithUser(Customer customer, UserRepository userRepository) {
        User user = new User();
        user.setRole("customer");
        User savedUser = userRepository.save(user);

        customer.setUser(savedUser);

        return save(customer);
    }
}
