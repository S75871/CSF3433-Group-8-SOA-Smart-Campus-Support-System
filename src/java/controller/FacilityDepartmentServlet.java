/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FacilityDepartmentServlet", urlPatterns = {"/FacilityDepartmentServlet"})
public class FacilityDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String description = request.getParameter("description");
        
        // STRICT DB ISOLATION: Connects ONLY to the Facility database
        String dbURL = "jdbc:mysql://localhost:3306/db_facility_service";
        String dbUser = "root";
        String dbPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
                
                // 1. RESOLVE workflow: Save to Facility Database
                String sql = "INSERT INTO facility_tickets (description, status) VALUES (?, 'Open')";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, description);
                    stmt.executeUpdate();
                }

                // 2. NOTIFY workflow: Call Notification Service via REST API
                callNotificationService(description, "Facility Department");
                
                // 3. Return 200 OK back to the Router
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void callNotificationService(String issue, String source) {
        try {
            URL url = new URL("http://localhost:8080/SmartCampusSupportSystem/NotificationServlet");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String postData = "issueInfo=" + URLEncoder.encode(issue, "UTF-8") + 
                              "&source=" + URLEncoder.encode(source, "UTF-8");
            
            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData.getBytes());
                os.flush();
            }
            conn.getResponseCode();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to call Notification Service: " + e.getMessage());
        }
    }
}