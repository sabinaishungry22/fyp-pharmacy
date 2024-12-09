package com.DAO;

import com.Model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_SUPPLIER_SQL = "INSERT INTO supplier (SupplierName, Address, MobileNo, Email, City, State, Zip, Balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SUPPLIERS = "SELECT * FROM supplier";
    private static final String UPDATE_SUPPLIER_SQL = "UPDATE supplier SET SupplierName = ?, Address = ?, MobileNo = ?, Email = ?, City = ?, State = ?, Zip = ?, Balance = ? WHERE ID = ?";
    private static final String DELETE_SUPPLIER_SQL = "DELETE FROM supplier WHERE ID = ?";
    private static final String SELECT_SUPPLIER_BY_ID = "SELECT * FROM supplier WHERE ID = ?";
    private static final String COUNT_SUPPLIERS_SQL = "SELECT COUNT(*) FROM supplier";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void insertSupplier(Supplier supplier) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUPPLIER_SQL)) {

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getMobileNo());
            preparedStatement.setString(4, supplier.getEmail());
            preparedStatement.setString(5, supplier.getCity());
            preparedStatement.setString(6, supplier.getState());
            preparedStatement.setString(7, supplier.getZip());
            preparedStatement.setDouble(8, supplier.getBalance());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SUPPLIERS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String supplierName = resultSet.getString("SupplierName");
                String address = resultSet.getString("Address");
                String mobileNo = resultSet.getString("MobileNo");
                String email = resultSet.getString("Email");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                String zip = resultSet.getString("Zip");
                double balance = resultSet.getDouble("Balance");

                Supplier supplier = new Supplier(id, supplierName, address, mobileNo, email, city, state, zip, balance);
                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return suppliers;
    }

    public Supplier getSupplierById(int supplierId) {
        Supplier supplier = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPPLIER_BY_ID)) {

            preparedStatement.setInt(1, supplierId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String supplierName = resultSet.getString("SupplierName");
                    String address = resultSet.getString("Address");
                    String mobileNo = resultSet.getString("MobileNo");
                    String email = resultSet.getString("Email");
                    String city = resultSet.getString("City");
                    String state = resultSet.getString("State");
                    String zip = resultSet.getString("Zip");
                    double balance = resultSet.getDouble("Balance");

                    supplier = new Supplier(id, supplierName, address, mobileNo, email, city, state, zip, balance);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return supplier;
    }

    public void updateSupplier(Supplier supplier) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUPPLIER_SQL)) {

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getMobileNo());
            preparedStatement.setString(4, supplier.getEmail());
            preparedStatement.setString(5, supplier.getCity());
            preparedStatement.setString(6, supplier.getState());
            preparedStatement.setString(7, supplier.getZip());
            preparedStatement.setDouble(8, supplier.getBalance());
            preparedStatement.setInt(9, supplier.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public void deleteSupplier(int supplierId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUPPLIER_SQL)) {

            preparedStatement.setInt(1, supplierId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }
    
    public int getTotalSuppliers() {
        int totalSuppliers = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUPPLIERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                totalSuppliers = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
        return totalSuppliers;
    }

    public int getSupplierCount() throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SUPPLIERS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        }
    }
   
}
