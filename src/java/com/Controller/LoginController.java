/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Controller;


import com.Model.User;
import com.DAO.UserInfo;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author USER
 */
public class LoginController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        UserInfo usersign = new UserInfo();
        User user = new User();
        user.setUsername((String) request.getParameter("username"));
        user.setPassword((String) request.getParameter("password"));
        user.setRole((String) request.getParameter("role"));
        user.setEmail((String) request.getParameter("email"));
        user.setPhone((String) request.getParameter("phone"));
        int status = usersign.signup(user);
        if (status > 0) {
            response.sendRedirect("Login.jsp");
        } else {
            response.sendRedirect("Signup.jsp");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        UserInfo userLog = new UserInfo();
        User user = new User();
        user.setUsername((String) request.getParameter("username"));
        user.setPassword((String) request.getParameter("password"));
        user.setRole((String) request.getParameter("role"));
        
        try {
            User loggedInUser = userLog.login(user);
            if (loggedInUser != null) {
                session.setAttribute("user", loggedInUser);

                // Redirect based on user role
                String role = loggedInUser.getRole();
                if ("Pharmacist".equals(role)) {
                    dispatcher = request.getRequestDispatcher("Home.jsp");
                } else if ("Admin".equals(role)) {
                    dispatcher = request.getRequestDispatcher("AdminHome.jsp");
                } else if ("Doctor".equals(role)) {
                    dispatcher = request.getRequestDispatcher("HealthHome.jsp");
                } else {
                    // Handle other roles or default redirection
                    dispatcher = request.getRequestDispatcher("Home.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("Login.jsp");
            }
        } catch (Exception e) {
            // Handle exceptions, log or redirect to an error page
            e.printStackTrace();
            dispatcher = request.getRequestDispatcher("Error.jsp");
        }

        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

