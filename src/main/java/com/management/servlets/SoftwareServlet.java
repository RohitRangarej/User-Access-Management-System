package com.management.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.management.entities.Software;
import com.management.services.SoftwareService;

@WebServlet(name = "SoftwareServlet", urlPatterns = "/createSoftware")
public class SoftwareServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private SoftwareService softwareService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] accessLevels = request.getParameterValues("accessLevels");

        String accessLevelsString = accessLevels != null ? String.join(", ", accessLevels) : "";

        Software software = new Software();
        software.setName(name);
        software.setDescription(description);
        software.setAccessLevels(accessLevelsString);

        softwareService.createSoftware(software);
        
        request.setAttribute("successMessage", "Software created");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createSoftware.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	String name=session.getAttribute("name").toString();
    	if(name.equals("Admin")) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createSoftware.jsp");
            dispatcher.forward(request, response);
    	}
    	else {
    		response.sendRedirect("/");
    	}
    }
}
