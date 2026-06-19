/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1. THE API ROUTE (This makes the servlet accessible via a web URL)
@WebServlet(name = "NotificationServlet", urlPatterns = {"/NotificationServlet"})
public class NotificationServlet extends HttpServlet {

    // 2. THE API LISTENER (This method 'listens' for the POST request we sent above)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 3. EXTRACTING THE DATA (Pulling the payload out of the API request)
        String issue = request.getParameter("issueInfo");
        String source = request.getParameter("source");
        
        // This is a stateless service simulating an email dispatch
        System.out.println("\n=====================================================");
        System.out.println("📧 [NOTIFICATION SERVICE] Signal Received!");
        System.out.println("Routed From: " + source);
        System.out.println("Status: Dispatching automated email to User/Committee...");
        System.out.println("Email Body: Your report regarding '" + issue + "' has been successfully logged into our system.");
        System.out.println("=====================================================\n");
        
        // 4. SENDING THE API STATUS CODE (Returning '200 OK' to say "I got it!")
        response.setStatus(HttpServletResponse.SC_OK);
    }
}