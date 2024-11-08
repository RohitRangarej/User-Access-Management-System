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
import com.management.entities.Users;
import com.management.services.RequestService;
import com.management.services.UserService;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private RequestService requestService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.authenticate(username, password) != null) {
        	HttpSession session = request.getSession();
        	session.setAttribute("name", username);
        	session.setAttribute("password", password);
        	Users user = userService.authenticate(username, password);
        	session.setAttribute("role", user.getRole());
            String role = user.getRole();
            switch (role) {
                case "Employee":
                    response.sendRedirect("requestAccess");
                    break;
                case "Manager":
                	List<Request> pendingRequests=new ArrayList<>();
                	for(Request requests : requestService.getAllRequests()) {
                		if(requests.getStatus().equals("Pending")) {
                			pendingRequests.add(requests);
                		}
                	}
                	request.setAttribute("pendingRequests", pendingRequests);
                	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pendingRequests.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "Admin":
                    response.sendRedirect("createSoftware");
                    break;
            }
        } else {
        	request.setAttribute("errorMessage", "Invalid credentials");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }
}
