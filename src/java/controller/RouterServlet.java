/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RouterServlet", urlPatterns = {"/RouterServlet"})
public class RouterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Extract the issue description submitted by the user from the JSP form
        String issue = request.getParameter("issueText");

        // 2. Define the API endpoint of the independent Core Service (ITDepartmentServlet)
        String targetURL = "http://localhost:8080/SmartCampusSupportSystem/ITDepartmentServlet";

        // 3. Initialize an HTTP connection to act as a network bridge between services
        URL url = new URL(targetURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // 4. Send the payload across the network to the Core Service
        try (OutputStream os = conn.getOutputStream()) {
            String postData = "description=" + URLEncoder.encode(issue, "UTF-8");
            byte[] input = postData.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }

        // 5. Evaluate the HTTP response code returned by the IT Service
        int responseCode = conn.getResponseCode();

        if (responseCode == 200) {
            // If the core service successfully processed the data, redirect to success page
            response.sendRedirect("success.jsp");
        } else {
            // If the core service is down or failed (Fault Tolerance handling)
            response.getWriter().println("System Error: Could not connect to the Core IT Service. Please try again later.");
        }
    }
}
