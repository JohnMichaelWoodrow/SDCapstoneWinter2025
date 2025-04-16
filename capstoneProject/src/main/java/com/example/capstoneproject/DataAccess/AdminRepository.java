package com.example.capstoneproject.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.capstoneproject.Users.Admin;
import com.example.capstoneproject.Users.User;

/**
 * Repository interface for managing Admin entities.
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Saves an Admin entity along with an associated User entity.
     * Ensures that the Admin is properly linked to a newly created User.
     *
     * @param admin The Admin entity to be saved.
     * @param userRepository The UserRepository used to persist the associated User.
     * @return The saved Admin entity.
     */
    @Transactional
    default Admin saveWithUser(Admin admin, UserRepository userRepository) {
        User user = new User();
        user.setRole("admin");
        User savedUser = userRepository.save(user);

        admin.setUser(savedUser);

        return save(admin);
    }
}
