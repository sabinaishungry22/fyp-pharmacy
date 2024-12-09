package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.DAO.UnitDao;
import com.Model.Unit;

@WebServlet("/UnitController")
public class UnitController extends HttpServlet {
    private UnitDao unitDAO;

    public void init() {
        unitDAO = new UnitDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateUnit(request, response);
        } else {
            // Handle the form submission for adding units
            String unitName = request.getParameter("unitName");
            String status = request.getParameter("status");

            Unit newUnit = new Unit(0, unitName, status);
            unitDAO.insertUnit(newUnit);

            response.sendRedirect("ManageUnit.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteUnit(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("ManageUnit.jsp"); // Redirect to the main page if action is not recognized
            }
        } else {
            response.sendRedirect("ManageUnit.jsp"); // Redirect to the main page if no action specified
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the unit ID from the request parameter
        int unitId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the unit by ID
        Unit unit = unitDAO.getUnitById(unitId);

        // Pass the unit object to the JSP page for editing
        request.setAttribute("unit", unit);

        // Forward to the JSP page for editing
        request.getRequestDispatcher("EditUnit.jsp").forward(request, response);
    }

    private void updateUnit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle the form submission for updating units
        int id = Integer.parseInt(request.getParameter("id"));
        String unitName = request.getParameter("unitName");
        String status = request.getParameter("status");

        Unit updatedUnit = new Unit(id, unitName, status);

        // Update the unit using DAO
        unitDAO.updateUnit(updatedUnit);

        response.sendRedirect("ManageUnit.jsp");
    }

    private void deleteUnit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the unit ID from the request parameter
        int unitId = Integer.parseInt(request.getParameter("id"));

        // Delete the unit by ID
        unitDAO.deleteUnit(unitId);

        response.sendRedirect("ManageUnit.jsp");
    }
}
