package com.DAO;

import com.Model.Purchase;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_PURCHASE_SQL = "INSERT INTO purchase (supplierId, batchId, expiryDate, stockQty, boxPattern, boxQty, quantity, supplierPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PURCHASES = "SELECT * FROM purchase";
    private static final String UPDATE_PURCHASE_SQL = "UPDATE purchase SET supplierId = ?, batchId = ?, expiryDate = ?, stockQty = ?, boxPattern = ?, boxQty = ?, quantity = ?, supplierPrice = ? WHERE id = ?";
    private static final String DELETE_PURCHASE_SQL = "DELETE FROM purchase WHERE id = ?";
    private static final String SELECT_PURCHASE_BY_ID = "SELECT * FROM purchase WHERE id = ?";
    private static final String COUNT_PURCHASES_SQL = "SELECT COUNT(*) FROM purchase";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void insertPurchase(Purchase purchase) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PURCHASE_SQL)) {

            preparedStatement.setInt(1, purchase.getSupplierId());
            preparedStatement.setString(2, purchase.getBatchId());
            preparedStatement.setDate(3, purchase.getExpiryDate());
            preparedStatement.setBigDecimal(4, purchase.getStockQty());
            preparedStatement.setString(5, purchase.getBoxPattern());
            preparedStatement.setInt(6, purchase.getBoxQty());
            preparedStatement.setInt(7, purchase.getQuantity());
            preparedStatement.setBigDecimal(8, purchase.getSupplierPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PURCHASES)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int supplierId = resultSet.getInt("supplierId");
                String batchId = resultSet.getString("batchId");
                Date expiryDate = resultSet.getDate("expiryDate");
                BigDecimal stockQty = resultSet.getBigDecimal("stockQty");
                String boxPattern = resultSet.getString("boxPattern");
                int boxQty = resultSet.getInt("boxQty");
                int quantity = resultSet.getInt("quantity");
                BigDecimal supplierPrice = resultSet.getBigDecimal("supplierPrice");
                BigDecimal totalPurchasePrice = resultSet.getBigDecimal("totalPurchasePrice");

                Purchase purchase = new Purchase(id, supplierId, batchId, expiryDate, stockQty, boxPattern, boxQty, quantity, supplierPrice, totalPurchasePrice);
                purchases.add(purchase);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return purchases;
    }

    public Purchase getPurchaseById(int purchaseId) {
        Purchase purchase = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASE_BY_ID)) {

            preparedStatement.setInt(1, purchaseId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int supplierId = resultSet.getInt("supplierId");
                    String batchId = resultSet.getString("batchId");
                    Date expiryDate = resultSet.getDate("expiryDate");
                    BigDecimal stockQty = resultSet.getBigDecimal("stockQty");
                    String boxPattern = resultSet.getString("boxPattern");
                    int boxQty = resultSet.getInt("boxQty");
                    int quantity = resultSet.getInt("quantity");
                    BigDecimal supplierPrice = resultSet.getBigDecimal("supplierPrice");
                    BigDecimal totalPurchasePrice = resultSet.getBigDecimal("totalPurchasePrice");

                    purchase = new Purchase(id, supplierId, batchId, expiryDate, stockQty, boxPattern, boxQty, quantity, supplierPrice, totalPurchasePrice);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return purchase;
    }

    public void updatePurchase(Purchase purchase) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PURCHASE_SQL)) {

            preparedStatement.setInt(1, purchase.getSupplierId());
            preparedStatement.setString(2, purchase.getBatchId());
            preparedStatement.setDate(3, purchase.getExpiryDate());
            preparedStatement.setBigDecimal(4, purchase.getStockQty());
            preparedStatement.setString(5, purchase.getBoxPattern());
            preparedStatement.setInt(6, purchase.getBoxQty());
            preparedStatement.setInt(7, purchase.getQuantity());
            preparedStatement.setBigDecimal(8, purchase.getSupplierPrice());
            preparedStatement.setInt(9, purchase.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public void deletePurchase(int purchaseId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PURCHASE_SQL)) {

            preparedStatement.setInt(1, purchaseId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public int getPurchaseCount() throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PURCHASES_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        }
    }
}
