<%-- 
    Document   : index
    Created on : 7 Jun 2026
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Campus Support Desk</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                padding: 50px;
            }
            .card {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
                max-width: 400px;
                margin: auto;
            }
            input, button {
                display: block;
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                box-sizing: border-box;
            }
            button {
                background-color: #007BFF;
                color: white;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <h2>Submit IT Issue</h2>

            <form action="RouterServlet" method="POST">
                <label>Describe your problem:</label>
                <input type="text" name="issueText" required placeholder="e.g., The projector is broken...">
                <button type="submit">Submit Ticket</button>
            </form>
        </div>
    </body>
</html>