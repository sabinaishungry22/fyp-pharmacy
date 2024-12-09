package com.Controller;

import com.DAO.StockDao;
import com.Model.Stock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/StockController")
public class StockController extends HttpServlet {
    private StockDao stockDao;

    public void init() {
        stockDao = new StockDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateStock(request, response);
        } else {
            String itemName = request.getParameter("itemName");
            String category = request.getParameter("category");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            LocalDate expiryDate = null;
            String expiryDateString = request.getParameter("expdate");
            
            if (expiryDateString != null && !expiryDateString.isEmpty()) {
                try {
                    expiryDate = LocalDate.parse(expiryDateString);
                } catch (DateTimeParseException e) {
                    // Handle parsing error
                    e.printStackTrace();
                    // Redirect or display an error message to the user
                    return;
                }
            }
            
            String supplier = request.getParameter("supplier");
            String batchNumber = request.getParameter("batchNumber");
            String location = request.getParameter("location");
            LocalDate lastUpdated = LocalDate.now();
            String status = request.getParameter("status"); // Assuming default status is Active

            Stock newStock = new Stock(0, itemName, category, quantity, price, expiryDate, supplier, batchNumber, location, lastUpdated, status);
            stockDao.insertStock(newStock);

            response.sendRedirect("ManageStock.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteStock(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    response.sendRedirect("ManageStock.jsp"); // Redirect to the main page if action is not recognized
            }
        } else {
            response.sendRedirect("ManageStock.jsp"); // Redirect to the main page if no action specified
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stockId = Integer.parseInt(request.getParameter("id"));
        Stock stock = stockDao.getStockById(stockId);
        request.setAttribute("stock", stock);
        request.getRequestDispatcher("EditStock.jsp").forward(request, response);
    }

    private void updateStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String itemName = request.getParameter("itemName");
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        LocalDate expiryDate = null;
        String expiryDateString = request.getParameter("expdate");
        
        if (expiryDateString != null && !expiryDateString.isEmpty()) {
            try {
                expiryDate = LocalDate.parse(expiryDateString);
            } catch (DateTimeParseException e) {
                // Handle parsing error
                e.printStackTrace();
                // Redirect or display an error message to the user
                return;
            }
        }
        
        String supplier = request.getParameter("supplier");
        String batchNumber = request.getParameter("batchNumber");
        String location = request.getParameter("location");
        LocalDate lastUpdated = LocalDate.now();
        String status = request.getParameter("status"); // Assuming default status is Active

        Stock updatedStock = new Stock(id, itemName, category, quantity, price, expiryDate, supplier, batchNumber, location, lastUpdated, status);
        stockDao.updateStock(updatedStock);

        response.sendRedirect("ManageStock.jsp");
    }

    private void deleteStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int stockId = Integer.parseInt(request.getParameter("id"));
        stockDao.deleteStock(stockId);
        response.sendRedirect("ManageStock.jsp");
    }
}
