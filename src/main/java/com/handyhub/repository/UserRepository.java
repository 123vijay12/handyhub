package com.handyhub.repository;

import com.handyhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query examples:
    Optional<User> findByUsername(String username);

    List<User> findByIsActive(boolean isActive);
}

