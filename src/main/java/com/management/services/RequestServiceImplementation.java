package com.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.entities.Request;
import com.management.entities.Software;
import com.management.entities.Users;
import com.management.repository.RequestRepository;
import com.management.repository.SoftwareRepository;

@Service
public class RequestServiceImplementation implements RequestService {

	@Autowired
    private RequestRepository requestRepository;

    @Autowired
    private SoftwareRepository softwareRepository;
    
    @Autowired
    private UserService userService;
    
    @Override
    public void submitRequest(Long softwareId, String accessType, String reason, String userName, String password) {
    	Users user=userService.authenticate(userName, password);
        Request request = new Request();
        request.setSoftware(softwareRepository.findById(softwareId).orElse(null));
        request.setAccessType(accessType);
        request.setReason(reason);
        request.setUser(user);
        requestRepository.save(request);
    }

    @Override
    public List<Software> getAllSoftware() {
        return softwareRepository.findAll();
    }

	@Override
	public void approvalRequest(Long requestId, String action) {
		Request accessRequest = requestRepository.findById(requestId).orElse(null);
        if (accessRequest != null) {
            if ("approve".equalsIgnoreCase(action)) {
                accessRequest.setStatus("Approved");
            } else if ("reject".equalsIgnoreCase(action)) {
                accessRequest.setStatus("Rejected");
            }
            requestRepository.save(accessRequest);
        }
	}

	@Override
	public List<Request> getAllRequests() {
		// TODO Auto-generated method stub
		return requestRepository.findAll();
	}

	@Override
	public Request getRequest(Long requestId) {
		// TODO Auto-generated method stub
		return requestRepository.findById(requestId).get();
	}

	@Override
	public void saveRequest(Request request) {
		// TODO Auto-generated method stub
		requestRepository.save(request);
	}

	@Override
	public void deleteRequest(Request request) {
		// TODO Auto-generated method stub
		requestRepository.deleteById(request.getId());
	}

}
