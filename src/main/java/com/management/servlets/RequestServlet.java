package com.management.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.entities.Request;
import com.management.services.RequestService;
import com.management.services.SoftwareService;

@WebServlet(name = "RequestServlet", urlPatterns = "/requestAccess")
public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private RequestService requestService;
	
	@Autowired
    private SoftwareService softwareService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        Long softwareId = Long.parseLong(request.getParameter("softwareId"));
        String[] accessLevels = request.getParameterValues("accessLevels");
        String reason = request.getParameter("reason");
        
        String accessLevelsString = (accessLevels != null) ? String.join(", ", accessLevels) : "";

        requestService.submitRequest(softwareId, accessLevelsString, reason, session.getAttribute("name").toString(), session.getAttribute("password").toString());
        
        String name=session.getAttribute("name").toString();
    	List<Request> pendingRequests=new ArrayList<>();
    	for(Request requests : requestService.getAllRequests()) {
    		if(requests.getStatus().equals("Pending") && requests.getUser().getUsername().equals(name)) {
    			pendingRequests.add(requests);
    		}
    	}
    	List<Request> actionedRequests=new ArrayList<>();
    	for(Request requests : requestService.getAllRequests()) {
    		if(requests.getStatus().equals("Approve") || requests.getStatus().equals("Reject")) {
    			if(requests.getUser().getUsername().equals(name)) {
    				actionedRequests.add(requests);
    			}
    		}
    	}
    	request.setAttribute("pendingRequests", pendingRequests);
    	request.setAttribute("actionedRequests", actionedRequests);
    	request.setAttribute("softwareList", softwareService.getAllSoftware());
        request.setAttribute("softwareList", softwareService.getAllSoftware());
        request.setAttribute("successMessage", "Request submitted");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/requestAccess.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	String name=session.getAttribute("name").toString();
    	List<Request> pendingRequests=new ArrayList<>();
    	for(Request requests : requestService.getAllRequests()) {
    		if(requests.getStatus().equals("Pending") && requests.getUser().getUsername().equals(name)) {
    			pendingRequests.add(requests);
    		}
    	}
    	List<Request> actionedRequests=new ArrayList<>();
    	for(Request requests : requestService.getAllRequests()) {
    		if(requests.getStatus().equals("Approve") || requests.getStatus().equals("Reject")) {
    			if(requests.getUser().getUsername().equals(name)) {
    				actionedRequests.add(requests);
    			}
    		}
    	}
    	request.setAttribute("pendingRequests", pendingRequests);
    	request.setAttribute("actionedRequests", actionedRequests);
    	request.setAttribute("softwareList", softwareService.getAllSoftware());
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/requestAccess.jsp");
        dispatcher.forward(request, response);
    }
}
