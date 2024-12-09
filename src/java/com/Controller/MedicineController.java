package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.DAO.MedicineDao;
import com.Model.Medicine;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/MedicineController")
public class MedicineController extends HttpServlet {
    private MedicineDao medicineDAO;

    public void init() {
        medicineDAO = new MedicineDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            try {
                updateMedicine(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                addMedicine(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    try {
                        deleteMedicine(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("ManageMedicine.jsp");
            }
        } else {
            response.sendRedirect("ManageMedicine.jsp");
        }
    }

    private void updateMedicine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String medicineName = request.getParameter("medicineName");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int unitId = Integer.parseInt(request.getParameter("unitId"));
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String expiryDateString = request.getParameter("expiryDate");

            // Log the received parameters
            System.out.println("Received parameters:");
            System.out.println("id: " + id);
            System.out.println("medicineName: " + medicineName);
            System.out.println("categoryId: " + categoryId);
            System.out.println("unitId: " + unitId);
            System.out.println("typeId: " + typeId);
            System.out.println("quantity: " + quantity);
            System.out.println("price: " + price);
            System.out.println("expiryDate: " + expiryDateString);

            LocalDate expiryDate = null;
            if (expiryDateString != null && !expiryDateString.trim().isEmpty()) {
                try {
                    expiryDate = LocalDate.parse(expiryDateString);
                } catch (DateTimeParseException e) {
                    throw new ServletException("Invalid expiry date format", e);
                }
            } else {
                throw new ServletException("Expiry date is required");
            }

            Medicine updatedMedicine = new Medicine(id, medicineName, categoryId, unitId, typeId, quantity, price, expiryDate);
            medicineDAO.updateMedicine(updatedMedicine);

            response.sendRedirect("ManageMedicine.jsp");
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new ServletException("Invalid input data", e);
        }
    }

    private void addMedicine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            String medicineName = request.getParameter("medicineName");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int unitId = Integer.parseInt(request.getParameter("unitId"));
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String expiryDateString = request.getParameter("expiryDate");

            LocalDate expiryDate = null;
            if (expiryDateString != null && !expiryDateString.trim().isEmpty()) {
                try {
                    expiryDate = LocalDate.parse(expiryDateString);
                } catch (DateTimeParseException e) {
                    throw new ServletException("Invalid expiry date format", e);
                }
            } else {
                throw new ServletException("Expiry date is required");
            }

            Medicine newMedicine = new Medicine(medicineName, categoryId, unitId, typeId, quantity, price, expiryDate);
            medicineDAO.insertMedicine(newMedicine);

            response.sendRedirect("ManageMedicine.jsp");
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new ServletException("Invalid input data", e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int medicineId = Integer.parseInt(request.getParameter("id"));
        Medicine medicine = medicineDAO.getMedicineById(medicineId);
        request.setAttribute("medicine", medicine);
        request.getRequestDispatcher("EditMedicine.jsp").forward(request, response);
    }

    private void deleteMedicine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int medicineId = Integer.parseInt(request.getParameter("id"));
        medicineDAO.deleteMedicine(medicineId);
        response.sendRedirect("ManageMedicine.jsp");
    }

}
