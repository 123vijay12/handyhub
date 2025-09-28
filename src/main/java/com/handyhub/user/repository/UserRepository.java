package com.handyhub.user.repository;

import com.handyhub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query examples:
    Optional<User> findByUsername(String username);

    List<User> findByIsActive(boolean isActive);

    Optional<User> findByContactInfoEmailAndPassword(String email, String password);

    Optional<User> findByContactInfoEmail(String email);

    // Check if email exists
    boolean existsByContactInfoEmail(String email);

    Optional<User> findByContactInfo_Email(String email);
    boolean existsByContactInfo_Email(String email);
}

