package com.DAO;

import com.Model.Prescription;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_PRESCRIPTION_SQL = "INSERT INTO prescription (patientName, doctorName, medicineName, dosage, quantity, prescriptionDate, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PRESCRIPTION_BY_ID = "SELECT * FROM prescription WHERE id = ?";
    private static final String SELECT_ALL_PRESCRIPTIONS = "SELECT * FROM prescription";
    private static final String DELETE_PRESCRIPTION_SQL = "DELETE FROM prescription WHERE id = ?";
    private static final String UPDATE_PRESCRIPTION_SQL = "UPDATE prescription SET patientName = ?, doctorName = ?, medicineName = ?, dosage = ?, quantity = ?, prescriptionDate = ?, notes = ? WHERE id = ?";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrescriptionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void insertPrescription(Prescription prescription) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRESCRIPTION_SQL)) {
            preparedStatement.setString(1, prescription.getPatientName());
            preparedStatement.setString(2, prescription.getDoctorName());
            preparedStatement.setString(3, prescription.getMedicineName());
            preparedStatement.setString(4, prescription.getDosage());
            preparedStatement.setInt(5, prescription.getQuantity());
            preparedStatement.setDate(6, prescription.getPrescriptionDate());
            preparedStatement.setString(7, prescription.getNotes());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Prescription selectPrescription(int id) {
        Prescription prescription = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRESCRIPTION_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String patientName = rs.getString("patientName");
                String doctorName = rs.getString("doctorName");
                String medicineName = rs.getString("medicineName");
                String dosage = rs.getString("dosage");
                int quantity = rs.getInt("quantity");
                Date prescriptionDate = rs.getDate("prescriptionDate");
                String notes = rs.getString("notes");
                prescription = new Prescription(id, patientName, doctorName, medicineName, dosage, quantity, prescriptionDate, notes);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return prescription;
    }

    public List<Prescription> selectAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRESCRIPTIONS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String patientName = rs.getString("patientName");
                String doctorName = rs.getString("doctorName");
                String medicineName = rs.getString("medicineName");
                String dosage = rs.getString("dosage");
                int quantity = rs.getInt("quantity");
                Date prescriptionDate = rs.getDate("prescriptionDate");
                String notes = rs.getString("notes");
                prescriptions.add(new Prescription(id, patientName, doctorName, medicineName, dosage, quantity, prescriptionDate, notes));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return prescriptions;
    }

    public boolean deletePrescription(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRESCRIPTION_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePrescription(Prescription prescription) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRESCRIPTION_SQL)) {
            statement.setString(1, prescription.getPatientName());
            statement.setString(2, prescription.getDoctorName());
            statement.setString(3, prescription.getMedicineName());
            statement.setString(4, prescription.getDosage());
            statement.setInt(5, prescription.getQuantity());
            statement.setDate(6, prescription.getPrescriptionDate());
            statement.setString(7, prescription.getNotes());
            statement.setInt(8, prescription.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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
