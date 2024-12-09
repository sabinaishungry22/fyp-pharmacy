package com.Controller;

import com.DAO.SupplierDao;
import com.Model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
    private SupplierDao supplierDAO;

    public void init() {
        supplierDAO = new SupplierDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateSupplier(request, response);
        } else {
            String supplierName = request.getParameter("supplierName");
            String address = request.getParameter("address");
            String mobileNo = request.getParameter("mobileNo");
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");
            double balance = Double.parseDouble(request.getParameter("balance"));

            Supplier newSupplier = new Supplier(0, supplierName, address, mobileNo, email, city, state, zip, balance);
            supplierDAO.insertSupplier(newSupplier);

            response.sendRedirect("SupplierList.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteSupplier(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("SupplierList.jsp"); // Redirect to the main page if action is not recognized
            }
        } else {
            response.sendRedirect("SupplierList.jsp"); // Redirect to the main page if no action specified
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int supplierId = Integer.parseInt(request.getParameter("id"));
        Supplier supplier = supplierDAO.getSupplierById(supplierId);
        request.setAttribute("supplier", supplier);
        request.getRequestDispatcher("EditSupplier.jsp").forward(request, response);
    }

    private void updateSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String supplierName = request.getParameter("supplierName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        double balance = Double.parseDouble(request.getParameter("balance"));

        Supplier updatedSupplier = new Supplier(id, supplierName, address, mobileNo, email, city, state, zip, balance);
        supplierDAO.updateSupplier(updatedSupplier);

        response.sendRedirect("SupplierList.jsp");
    }

    private void deleteSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int supplierId = Integer.parseInt(request.getParameter("id"));
        supplierDAO.deleteSupplier(supplierId);
        response.sendRedirect("SupplierList.jsp");
    }
    
    private void getTotalSuppliers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalSuppliers = supplierDAO.getTotalSuppliers();
        request.setAttribute("totalSuppliers", totalSuppliers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
        dispatcher.forward(request, response);
}
}