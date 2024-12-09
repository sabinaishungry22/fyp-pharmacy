

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pharmacy User Registration - Sign Up</title>
    <style>
        body {
            background-image: url(https://img.freepik.com/free-photo/blur-shopping-mall_1203-8319.jpg?w=996&t=st=1704346611~exp=1704347211~hmac=5f1e062cd00981e92e7846473d9212e7b8bf55311542f4a7991c0ae8214fbac1);
            font-family: Arial, sans-serif;
            background-size: cover;
            background-position: center;
            margin: 0;
            height: 100vh;
            overflow: hidden;
        }

        .container {
        max-width: 400px;
        margin: 100px auto 0; /* Adjust the margin-top value as needed */
        padding: 30px;
        background-color: white;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

        h2 {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"],
        .signup-button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #506982;
            color: white;
            margin-bottom: 10px;
        }

        .signup-button {
            background-color: #00cc00;
            color: white;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }

        .radio-container {
            display: inline-block;
            margin-right: 10px;
        }

        .radio-container label {
            display: inline;
            vertical-align: middle;
            margin-right: 5px;
        }

        .logo {
            text-align: center;
            margin-bottom: 20px;
        }

        .logo img {
            width: 200px;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logo">
        <!-- <img src="https://i.pinimg.com/280x280_RS/26/4b/7c/264b7ca16c4789fd97bc3b330e41d573.jpg" alt="Logo"> -->
    </div>
        <h2>Sign Up</h2>
        <form action="LoginController" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter the username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter the password" required>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter the email" required>
            <label for="email">Phone Num:</label>
            <input type="text" id="phone" name="phone" placeholder="Enter phone number" required>
            
            <label>Role:</label>
        <div class="radio-container">
            <input type="radio" id="pharmacist" name="role" value="pharmacist" required>
            <label for="teacher">Pharmacist</label>
        </div>

        <div class="radio-container">
            <input type="radio" id="admin" name="role" value="admin" required>
            <label for="staff">Admin</label>
        </div>

        <div class="radio-container">
            <input type="radio" id="doctor" name="role" value="doctor" required>
            <label for="student">Doctor</label>
        </div><br><br>

  
            <input type="submit" value="Sign Up">
            <button onclick="window.location.href ='Login.jsp'" class="signup-button"> Login </button>
        </form>
       
        <%-- Display error message if registration fails --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
    </div>
    
</body>
</html>
