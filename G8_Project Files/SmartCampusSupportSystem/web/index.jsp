<%-- 
    Document   : index
    Created on : 7 Jun 2026
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Campus Support System</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 450px;
        }
        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        label {
            font-weight: 600;
            color: #34495e;
            font-size: 14px;
        }
        select, input[type="text"] {
            width: 100%;
            padding: 12px;
            margin: 8px 0 25px 0;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        select:focus, input[type="text"]:focus {
            outline: none;
            border-color: #3498db;
            box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
        }
        button {
            width: 100%;
            background-color: #3498db;
            color: white;
            padding: 14px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Campus Support Portal</h2>
        
        <form action="RouterServlet" method="POST">
            
            <label for="department">Select Support Department:</label>
            <select name="department" id="department" required>
                <option value="" disabled selected>-- Select a Department --</option>
                <option value="IT">IT Issue (e.g., Broken Projector / Mic)</option>
                <option value="Facility">Facility Issue (e.g., Broken Chair / Table)</option>
            </select>
            
            <label for="issueText">Issue / Request Details:</label>
            <input type="text" name="issueText" id="issueText" placeholder="Briefly describe the issue..." required autocomplete="off">
            
            <button type="submit">Submit Report</button>
        </form>
    </div>
</body>
</html>