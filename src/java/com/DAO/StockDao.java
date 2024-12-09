/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;


import com.Model.Stock;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class StockDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_STOCK_SQL = "INSERT INTO stocks (ItemName, Category, Quantity, Price, ExpDate, Supplier, BatchNumber, Location, LastUpdated, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_STOCKS = "SELECT * FROM stocks";
    private static final String UPDATE_STOCK_SQL = "UPDATE stocks SET ItemName = ?, Category = ?, Quantity = ?, Price = ?, ExpDate = ?, Supplier = ?, BatchNumber = ?, Location = ?, LastUpdated = ?, Status = ? WHERE ID = ?";
    private static final String DELETE_STOCK_SQL = "DELETE FROM stocks WHERE ID = ?";
    private static final String SELECT_STOCK_BY_ID = "SELECT * FROM stocks WHERE ID = ?";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StockDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void insertStock(Stock stock) {
    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STOCK_SQL)) {

        preparedStatement.setString(1, stock.getItemName());
        preparedStatement.setString(2, stock.getCategory());
        preparedStatement.setInt(3, stock.getQuantity());
        preparedStatement.setDouble(4, stock.getPrice());
        
        // Check if expiryDate is not null before setting it
        LocalDate expiryDate = stock.getExpiryDate();
        if (expiryDate != null) {
            preparedStatement.setDate(5, java.sql.Date.valueOf(expiryDate));
        } else {
            preparedStatement.setNull(5, Types.DATE); // Set as NULL in the database
        }
        
        preparedStatement.setString(6, stock.getSupplier());
        preparedStatement.setString(7, stock.getBatchNumber());
        preparedStatement.setString(8, stock.getLocation());
        preparedStatement.setDate(9, java.sql.Date.valueOf(stock.getLastUpdated()));
        preparedStatement.setString(10, stock.getStatus());

        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions properly in a real application
    }
}


    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_STOCKS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String itemName = resultSet.getString("itemName");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                LocalDate expiryDate = resultSet.getDate("expdate").toLocalDate();
                String supplier = resultSet.getString("supplier");
                String batchNumber = resultSet.getString("batchNumber");
                String location = resultSet.getString("location");
                LocalDate lastUpdated = resultSet.getDate("lastUpdated").toLocalDate();
                String status = resultSet.getString("status");

                Stock stock = new Stock(id, itemName, category, quantity, price, expiryDate, supplier, batchNumber, location, lastUpdated, status);
                stocks.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return stocks;
    }

    public Stock getStockById(int stockId) {
        Stock stock = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_BY_ID)) {

            preparedStatement.setInt(1, stockId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String itemName = resultSet.getString("itemName");
                    String category = resultSet.getString("category");
                    int quantity = resultSet.getInt("quantity");
                    double price = resultSet.getDouble("price");
                    LocalDate expiryDate = resultSet.getDate("expdate").toLocalDate();
                    String supplier = resultSet.getString("supplier");
                    String batchNumber = resultSet.getString("batchNumber");
                    String location = resultSet.getString("location");
                    LocalDate lastUpdated = resultSet.getDate("lastUpdated").toLocalDate();
                    String status = resultSet.getString("status");

                    stock = new Stock(id, itemName, category, quantity, price, expiryDate, supplier, batchNumber, location, lastUpdated, status);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return stock;
    }

    public void updateStock(Stock stock) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK_SQL)) {

            preparedStatement.setString(1, stock.getItemName());
            preparedStatement.setString(2, stock.getCategory());
            preparedStatement.setInt(3, stock.getQuantity());
            preparedStatement.setDouble(4, stock.getPrice());
            preparedStatement.setDate(5, java.sql.Date.valueOf(stock.getExpiryDate()));
            preparedStatement.setString(6, stock.getSupplier());
            preparedStatement.setString(7, stock.getBatchNumber());
            preparedStatement.setString(8, stock.getLocation());
            preparedStatement.setDate(9, java.sql.Date.valueOf(stock.getLastUpdated()));
            preparedStatement.setString(10, stock.getStatus());
            preparedStatement.setInt(11, stock.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public void deleteStock(int stockId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STOCK_SQL)) {

            preparedStatement.setInt(1, stockId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }
}

