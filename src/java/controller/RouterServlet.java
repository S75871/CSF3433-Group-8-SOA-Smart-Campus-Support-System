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
        
        String dept = request.getParameter("department");
        String issue = request.getParameter("issueText");
        String targetURL = "";

        // SOA Routing Logic: Avoid one service doing everything
        if ("IT".equals(dept)) {
            targetURL = "http://localhost:8080/SmartCampusSupportSystem/ITDepartmentServlet";
        } else if ("Facility".equals(dept)) {
            targetURL = "http://localhost:8080/SmartCampusSupportSystem/FacilityDepartmentServlet";
        }

        try {
            // Forward request to the specific core service (Decomposition)
            URL url = new URL(targetURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String postData = "description=" + URLEncoder.encode(issue, "UTF-8");
            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            response.setContentType("text/html");
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response.getWriter().println("<div style='text-align:center; margin-top:50px; font-family:sans-serif;'>");
                response.getWriter().println("<h2 style='color:green;'>Success!</h2>");
                response.getWriter().println("<p>Your report has been successfully routed to the <b>" + dept + " Department</b>.</p>");
                response.getWriter().println("<p><i>(Please check NetBeans Output panel to see the mock email notification)</i></p>");
                response.getWriter().println("<br><a href='index.jsp'>Back to Home</a>");
                response.getWriter().println("</div>");
            } else {
                response.getWriter().println("<h3>System Error: Could not connect to the Core Service. Please try again later.</h3>");
            }
            conn.disconnect();

        } catch (Exception e) {
            response.getWriter().println("<h3>Gateway Error: " + e.getMessage() + "</h3>");
        }
    }
}
