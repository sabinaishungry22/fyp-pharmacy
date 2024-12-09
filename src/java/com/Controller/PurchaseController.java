package com.Controller;

import com.DAO.PurchaseDao;
import com.DAO.SupplierDao;
import com.Model.Purchase;
import com.Model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/PurchaseController")
public class PurchaseController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PurchaseController.class.getName());
    private PurchaseDao purchaseDao;

    public void init() {
        purchaseDao = new PurchaseDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LOGGER.log(Level.INFO, "doPost action: {0}", action);

        if ("update".equals(action)) {
            try {
                updatePurchase(request, response);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Update error", ex);
            }
        } else {
            try {
                addPurchase(request, response);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Add error", ex);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LOGGER.log(Level.INFO, "doGet action: {0}", action);

        if (action != null) {
            switch (action) {
                case "delete":
                    try {
                        deletePurchase(request, response);
                    } catch (SQLException ex) {
                        LOGGER.log(Level.SEVERE, "Delete error", ex);
                    }
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "generate":
                    generateReceipt(request, response);
                    break;
                default:
                    response.sendRedirect("Purchase.jsp");
            }
        } else {
            response.sendRedirect("Purchase.jsp");
        }
    }

    private void updatePurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            String batchId = request.getParameter("batchId");
            String expiryDateString = request.getParameter("expiryDate");
            BigDecimal stockQty = new BigDecimal(request.getParameter("stockQty"));
            String boxPattern = request.getParameter("boxPattern");
            int boxQty = Integer.parseInt(request.getParameter("boxQty"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            BigDecimal supplierPrice = new BigDecimal(request.getParameter("supplierPrice"));

            Date expiryDate = null;
            if (expiryDateString != null && !expiryDateString.trim().isEmpty()) {
                expiryDate = Date.valueOf(expiryDateString);
            } else {
                throw new ServletException("Expiry date is required");
            }

            Purchase updatedPurchase = new Purchase(id, supplierId, batchId, expiryDate, stockQty, boxPattern, boxQty, quantity, supplierPrice);
            purchaseDao.updatePurchase(updatedPurchase);

            response.sendRedirect("Purchase.jsp");
        } catch (NumberFormatException | DateTimeParseException e) {
            LOGGER.log(Level.SEVERE, "Invalid input data", e);
            throw new ServletException("Invalid input data", e);
        }
    }

    private void addPurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            String batchId = request.getParameter("batchId");
            String expiryDateString = request.getParameter("expiryDate");
            BigDecimal stockQty = new BigDecimal(request.getParameter("stockQty"));
            String boxPattern = request.getParameter("boxPattern");
            int boxQty = Integer.parseInt(request.getParameter("boxQty"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            BigDecimal supplierPrice = new BigDecimal(request.getParameter("supplierPrice"));

            Date expiryDate = null;
            if (expiryDateString != null && !expiryDateString.trim().isEmpty()) {
                expiryDate = Date.valueOf(expiryDateString);
            } else {
                throw new ServletException("Expiry date is required");
            }

            Purchase newPurchase = new Purchase(supplierId, batchId, expiryDate, stockQty, boxPattern, boxQty, quantity, supplierPrice);
            purchaseDao.insertPurchase(newPurchase);

            response.sendRedirect("Purchase.jsp");
        } catch (NumberFormatException | DateTimeParseException e) {
            LOGGER.log(Level.SEVERE, "Invalid input data", e);
            throw new ServletException("Invalid input data", e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int purchaseId = Integer.parseInt(request.getParameter("id"));
        Purchase purchase = purchaseDao.getPurchaseById(purchaseId);
        request.setAttribute("purchase", purchase);
        request.getRequestDispatcher("EditPurchase.jsp").forward(request, response);
    }

    private void deletePurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int purchaseId = Integer.parseInt(request.getParameter("id"));
        purchaseDao.deletePurchase(purchaseId);
        response.sendRedirect("Purchase.jsp");
    }

    private void generateReceipt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int purchaseId = Integer.parseInt(request.getParameter("id"));
    LOGGER.log(Level.INFO, "Generating receipt for purchase ID: {0}", purchaseId);

    Purchase purchase = purchaseDao.getPurchaseById(purchaseId);
    SupplierDao supplierDao = new SupplierDao();
    Supplier supplier = supplierDao.getSupplierById(purchase.getSupplierId());

    if (purchase != null && supplier != null) {
        LOGGER.log(Level.INFO, "Supplier fetched: {0}", supplier.getSupplierName());
        request.setAttribute("purchase", purchase);
        request.setAttribute("supplier", supplier);
        request.getRequestDispatcher("GenerateReceipt.jsp").forward(request, response);
    } else {
        if (purchase == null) {
            LOGGER.log(Level.SEVERE, "Purchase not found for ID: {0}", purchaseId);
        }
        if (supplier == null) {
            LOGGER.log(Level.SEVERE, "Supplier not found for ID: {0}", purchase.getSupplierId());
        }
        response.sendRedirect("Purchase.jsp");
    }
}

}
