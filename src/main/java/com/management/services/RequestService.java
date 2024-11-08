package com.management.services;

import java.util.List;

import com.management.entities.Request;
import com.management.entities.Software;

public interface RequestService {
    void submitRequest(Long softwareId, String accessType, String reason, String userName, String password);
    List<Software> getAllSoftware();
    void approvalRequest(Long requestId, String action);
    List<Request> getAllRequests();
    Request getRequest(Long requestId);
    void saveRequest(Request request);
    void deleteRequest(Request request);
}