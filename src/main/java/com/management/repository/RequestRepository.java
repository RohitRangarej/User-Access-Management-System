package com.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatus(String status);
}