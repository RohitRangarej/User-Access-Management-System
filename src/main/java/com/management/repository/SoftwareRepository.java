package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entities.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long> {
}