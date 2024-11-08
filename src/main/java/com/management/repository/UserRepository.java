package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
