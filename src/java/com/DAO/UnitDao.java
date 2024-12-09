package com.DAO;

import com.Model.Unit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnitDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_UNIT_SQL = "INSERT INTO unit (unit_name, status) VALUES (?, ?)";
    private static final String SELECT_ALL_UNITS = "SELECT * FROM unit";
    private static final String UPDATE_UNIT_SQL = "UPDATE unit SET unit_name = ?, status = ? WHERE id = ?";
    private static final String DELETE_UNIT_SQL = "DELETE FROM unit WHERE id = ?";
    private static final String SELECT_UNIT_BY_ID = "SELECT * FROM unit WHERE id = ?";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UnitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void insertUnit(Unit unit) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_UNIT_SQL)) {

            preparedStatement.setString(1, unit.getUnit_name());
            preparedStatement.setString(2, unit.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public List<Unit> getAllUnits() {
        List<Unit> units = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_UNITS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String unit_name = resultSet.getString("unit_name");
                String status = resultSet.getString("status");

                Unit unit = new Unit(id, unit_name, status);
                units.add(unit);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return units;
    }

    public Unit getUnitById(int unitId) {
        Unit unit = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNIT_BY_ID)) {

            preparedStatement.setInt(1, unitId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String unit_name = resultSet.getString("unit_name");
                    String status = resultSet.getString("status");

                    unit = new Unit(id, unit_name, status);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return unit;
    }

    public void updateUnit(Unit unit) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UNIT_SQL)) {

            preparedStatement.setString(1, unit.getUnit_name());
            preparedStatement.setString(2, unit.getStatus());
            preparedStatement.setInt(3, unit.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public void deleteUnit(int unitId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_UNIT_SQL)) {

            preparedStatement.setInt(1, unitId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }
}
