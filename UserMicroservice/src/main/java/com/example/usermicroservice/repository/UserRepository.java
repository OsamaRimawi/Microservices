package com.example.usermicroservice.repository;

import com.example.usermicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUserName(String userName);

    String deleteByUserName(String userName);
}