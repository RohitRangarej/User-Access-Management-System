package com.management.services;

import java.util.List;

import com.management.entities.Software;

public interface SoftwareService {
    void createSoftware(Software software);
    List<Software> getAllSoftware();
	Software getSoftwareById(Long id);
}