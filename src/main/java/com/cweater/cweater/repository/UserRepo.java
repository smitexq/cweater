package com.cweater.cweater.repository;

import com.cweater.cweater.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
