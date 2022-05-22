package rs.njmarko.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.njmarko.userservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
