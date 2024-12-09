/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.DAO.CategoryDao;
import com.Model.Category;

@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
    private CategoryDao categoryDAO;

    public void init() {
        categoryDAO = new CategoryDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateCategory(request, response);
        } else {
            // Handle the form submission for adding categories
            String categoryName = request.getParameter("categoryName");
            String status = request.getParameter("status");

            Category newCategory = new Category(0, categoryName, status);
            categoryDAO.insertCategory(newCategory);

            response.sendRedirect("ManageCategory.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteCategory(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("ManageCategory.jsp"); // Redirect to the main page if action is not recognized
            }
        } else {
            response.sendRedirect("ManageCategory.jsp"); // Redirect to the main page if no action specified
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the category ID from the request parameter
        int categoryId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the category by ID
        Category category = categoryDAO.getCategoryById(categoryId);

        // Pass the category object to the JSP page for editing
        request.setAttribute("category", category);

        // Forward to the JSP page for editing
        request.getRequestDispatcher("EditCategory.jsp").forward(request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle the form submission for updating categories
        int id = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("categoryName");
        String status = request.getParameter("status");

        Category updatedCategory = new Category(id, categoryName, status);

        // Update the category using DAO
        categoryDAO.updateCategory(updatedCategory);

        response.sendRedirect("ManageCategory.jsp");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the category ID from the request parameter
        int categoryId = Integer.parseInt(request.getParameter("id"));

        // Delete the category by ID
        categoryDAO.deleteCategory(categoryId);

        response.sendRedirect("ManageCategory.jsp");
    }
}
