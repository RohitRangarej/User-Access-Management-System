package com.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entities.Software;
import com.management.repository.SoftwareRepository;

@Service
public class SoftwareServiceImplementation implements SoftwareService {

	@Autowired
    private SoftwareRepository softwareRepository;

    @Override
    public void createSoftware(Software software) {
        softwareRepository.save(software);
    }

	@Override
	public List<Software> getAllSoftware() {
		return softwareRepository.findAll();
	}

	@Override
	public Software getSoftwareById(Long id) {
		// TODO Auto-generated method stub
		return softwareRepository.findById(id).get();
	}

}
