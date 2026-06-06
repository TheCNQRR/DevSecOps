package by.java.enterprise.authservice.repository;

import by.java.enterprise.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByUsername(String username);
}
