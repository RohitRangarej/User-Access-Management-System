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


@WebServlet(name = "ApprovalServlet", urlPatterns = "/approveRequest")
public class ApprovalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private RequestService requestService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Long requestId = Long.parseLong(request.getParameter("requestId"));
        String action = request.getParameter("action");
        
        Request requests=requestService.getRequest(requestId);
        requests.setStatus(action);
        requestService.saveRequest(requests);
        
        List<Request> pendingRequests=new ArrayList<>();
    	for(Request requestPen : requestService.getAllRequests()) {
    		if(requestPen.getStatus().equals("Pending")) {
    			pendingRequests.add(requestPen);
    		}
    	}
    	request.setAttribute("pendingRequests", pendingRequests);
    	request.setAttribute("actionMessage", action);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pendingRequests.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	String name=session.getAttribute("name").toString();
    	if(name.equals("Manager")) {
    		List<Request> pendingRequests=new ArrayList<>();
        	for(Request requestPen : requestService.getAllRequests()) {
        		if(requestPen.getStatus().equals("Pending")) {
        			pendingRequests.add(requestPen);
        		}
        	}
        	request.setAttribute("pendingRequests", pendingRequests);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pendingRequests.jsp");
            dispatcher.forward(request, response);
    	}
    	else {
    		response.sendRedirect("/");
    	}
    }
}
