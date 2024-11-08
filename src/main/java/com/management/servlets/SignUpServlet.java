package com.management.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.services.UserService;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(userService.exitsUser(username)) {
        	request.setAttribute("errorMessage", "Username already exists");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp?error=Username already exists");
            dispatcher.forward(request, response);
            return;
        }
        if(username.equals("Admin")) {
        	userService.registerUser(username, password, "Admin");
        }
        else if(username.equals("Manager")) {
        	userService.registerUser(username, password, "Manager");
        }
        else {
        	userService.registerUser(username, password, "Employee");
        }

        response.sendRedirect("login");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
        dispatcher.forward(request, response);
    }

}
