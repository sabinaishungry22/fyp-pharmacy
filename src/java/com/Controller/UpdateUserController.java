package com.Controller;

import com.DAO.UserInfo;
import com.Model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                int id = user.getId();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String role = user.getRole(); // Preserve the role from the session

                // Create a User object with updated information
                User updatedUser = new User();
                updatedUser.setId(id);
                updatedUser.setUsername(username);
                updatedUser.setPassword(password);
                updatedUser.setEmail(email);
                updatedUser.setPhone(phone);
                updatedUser.setRole(role); // Set the role from the session

                // Call the update method in UserInfo to update the user profile
                UserInfo userInfo = new UserInfo();
                boolean updateSuccess = userInfo.updateUser(updatedUser);

                if (updateSuccess) {
                    // Update the user object in session
                    session.setAttribute("user", updatedUser);
                    // Redirect to the profile page
                    response.sendRedirect("Account.jsp");
                } else {
                    // Redirect to an error page or display an error message
                    response.sendRedirect("UpdateError.jsp");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            response.sendRedirect("UpdateError.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UpdateError.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests if necessary
        doPost(request, response);
    }
}
