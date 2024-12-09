
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pharmacy - Login</title>
    <style>
         body {
            background-image: url(https://img.freepik.com/free-photo/blur-shopping-mall_1203-8319.jpg?w=996&t=st=1704346611~exp=1704347211~hmac=5f1e062cd00981e92e7846473d9212e7b8bf55311542f4a7991c0ae8214fbac1);
            font-family: -apple-system, BlinkMacSystemFont, sans-serif;
            background-size: cover; /* Ensures the background image covers the entire body without repetition */
        background-position: center;
        margin: 0; /* Remove default margin to ensure the image covers the entire viewport */
        height: 100vh; /* Set height to 100% of the viewport height */
        overflow: hidden; /* Hide overflow to prevent scrolling */
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
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #00cc00;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 10px;
        }
        

        .signup-button {
            width: 100%;
            padding: 10px;
            background-color: #506982;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
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
            width: 200px; /* Set the desired width */
            height: auto; /* Adjust height accordingly to maintain aspect ratio */
        }
    </style>
</head>
<body>
<div class="container">
    <div class="logo">
        <!-- <img src="https://i.pinimg.com/280x280_RS/26/4b/7c/264b7ca16c4789fd97bc3b330e41d573.jpg" alt="Logo"> -->
    </div>
    <h2>Login Page</h2>
    <form action="LoginController" method="get">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" placeholder="Enter the username" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter the password" required>

        <label>Role:</label>
        <div class="radio-container">
            <input type="radio" id="pharmacist" name="role" value="pharmacist" required>
            <label for="pharmacist">Pharmacist</label>
        </div>

        <div class="radio-container">
            <input type="radio" id="admin" name="role" value="admin" required>
            <label for="admin">Admin</label>
        </div>

        <div class="radio-container">
            <input type="radio" id="doctor" name="role" value="doctor" required>
            <label for="doctor">Doctor</label>
        </div><br><br>

        <input type="submit" value="Login">
        <button onclick="window.location.href ='Signup.jsp'" class="signup-button"> Signup </button>
    </form>
</div>

</body>
</html>





