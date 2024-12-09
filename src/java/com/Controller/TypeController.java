package com.Controller;

import com.DAO.TypeDao;
import com.Model.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TypeController")
public class TypeController extends HttpServlet {
    private TypeDao typeDao;

    public void init() {
        typeDao = new TypeDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateType(request, response);
        } else {
            // Handle the form submission for adding types
            String typeName = request.getParameter("typeName");
            String status = request.getParameter("status");

            Type newType = new Type(0, typeName, status);
            typeDao.insertType(newType);

            response.sendRedirect("ManageType.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteType(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("ManageType.jsp"); // Redirect to the main page if action is not recognized
            }
        } else {
            response.sendRedirect("ManageType.jsp"); // Redirect to the main page if no action specified
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the type ID from the request parameter
        int typeId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the type by ID
        Type type = typeDao.getTypeById(typeId);

        // Pass the type object to the JSP page for editing
        request.setAttribute("type", type);

        // Forward to the JSP page for editing
        request.getRequestDispatcher("EditType.jsp").forward(request, response);
    }

    private void updateType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle the form submission for updating types
        int id = Integer.parseInt(request.getParameter("id"));
        String typeName = request.getParameter("typeName");
        String status = request.getParameter("status");

        Type updatedType = new Type(id, typeName, status);

        // Update the type using DAO
        typeDao.updateType(updatedType);

        response.sendRedirect("ManageType.jsp");
    }

    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the type ID from the request parameter
        int typeId = Integer.parseInt(request.getParameter("id"));

        // Delete the type by ID
        typeDao.deleteType(typeId);

        response.sendRedirect("ManageType.jsp");
    }
}
