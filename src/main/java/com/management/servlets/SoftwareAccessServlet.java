package com.management.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.entities.Software;
import com.management.services.SoftwareService;

@WebServlet(name = "SoftwareAccessServlet", urlPatterns = "/getAvailableAccessLevels")
public class SoftwareAccessServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    @Autowired
    private SoftwareService softwareService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String softwareId = request.getParameter("softwareId");

        Software software = softwareService.getSoftwareById(Long.parseLong(softwareId));

        String accessLevels = software.getAccessLevels(); 

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("accessLevels", accessLevels.split(","));
        
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
