package com.DAO;

import com.Model.Medicine;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pharmacy";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";

    private static final String INSERT_MEDICINE_SQL = "INSERT INTO medicines (medicineName, categoryId, unitId, typeId, quantity, price, expiryDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_MEDICINE_BY_ID = "SELECT id, medicineName, categoryId, unitId, typeId, quantity, price, expiryDate FROM medicines WHERE id = ?";
    private static final String SELECT_ALL_MEDICINES = "SELECT * FROM medicines";
    private static final String DELETE_MEDICINE_SQL = "DELETE FROM medicines WHERE id = ?";
    private static final String UPDATE_MEDICINE_SQL = "UPDATE medicines SET medicineName = ?, categoryId = ?, unitId = ?, typeId = ?, quantity = ?, price = ?, expiryDate = ? WHERE id = ?";

    public MedicineDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertMedicine(Medicine medicine) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDICINE_SQL)) {
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setInt(2, medicine.getCategoryId());
            preparedStatement.setInt(3, medicine.getUnitId());
            preparedStatement.setInt(4, medicine.getTypeId());
            preparedStatement.setInt(5, medicine.getQuantity());
            preparedStatement.setDouble(6, medicine.getPrice());
            preparedStatement.setDate(7, Date.valueOf(medicine.getExpiryDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Medicine getMedicineById(int id) {
        Medicine medicine = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICINE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String medicineName = rs.getString("medicineName");
                int categoryId = rs.getInt("categoryId");
                int unitId = rs.getInt("unitId");
                int typeId = rs.getInt("typeId");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                LocalDate expiryDate = rs.getDate("expiryDate").toLocalDate();
                medicine = new Medicine(id, medicineName, categoryId, unitId, typeId, quantity, price, expiryDate);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return medicine;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICINES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String medicineName = rs.getString("medicineName");
                int categoryId = rs.getInt("categoryId");
                int unitId = rs.getInt("unitId");
                int typeId = rs.getInt("typeId");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                LocalDate expiryDate = rs.getDate("expiryDate").toLocalDate();
                medicines.add(new Medicine(id, medicineName, categoryId, unitId, typeId, quantity, price, expiryDate));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return medicines;
    }

    public boolean updateMedicine(Medicine medicine) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEDICINE_SQL)) {
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setInt(2, medicine.getCategoryId());
            preparedStatement.setInt(3, medicine.getUnitId());
            preparedStatement.setInt(4, medicine.getTypeId());
            preparedStatement.setInt(5, medicine.getQuantity());
            preparedStatement.setDouble(6, medicine.getPrice());
            preparedStatement.setDate(7, Date.valueOf(medicine.getExpiryDate()));
            preparedStatement.setInt(8, medicine.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteMedicine(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEDICINE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    
}
