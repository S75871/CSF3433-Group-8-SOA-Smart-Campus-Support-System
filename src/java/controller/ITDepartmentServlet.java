/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ITDepartmentServlet", urlPatterns = {"/ITDepartmentServlet"})
public class ITDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Receive the specific payload forwarded by the Router Gateway
        String description = request.getParameter("description");

        // 2. Define isolated database credentials for the IT department
        String dbURL = "jdbc:mysql://localhost:3306/campus_support_db";
        String dbUser = "root"; 
        String dbPass = "admin";     
        try {
            // 3. Load the MySQL JDBC Driver to enable database connectivity
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            // 4. Execute the SQL statement to securely insert the new ticket
            String sql = "INSERT INTO it_tickets (description, status) VALUES (?, 'Open')";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, description);
            statement.executeUpdate();

            // 5. Close the database connection to prevent memory leaks
            conn.close();

            // 6. Return a "200 OK" HTTP status back to the Router to confirm success
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            e.printStackTrace();

            // 7. If the database crashes, return a "500 Internal Server Error" to the Router
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}