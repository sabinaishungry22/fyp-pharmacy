package com.DAO;

import com.Model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_TYPE_SQL = "INSERT INTO types (type_name, status) VALUES (?, ?)";
    private static final String SELECT_ALL_TYPES = "SELECT * FROM types";
    private static final String UPDATE_TYPE_SQL = "UPDATE types SET type_name = ?, status = ? WHERE id = ?";
    private static final String DELETE_TYPE_SQL = "DELETE FROM types WHERE id = ?";
    private static final String SELECT_TYPE_BY_ID = "SELECT * FROM types WHERE id = ?";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void insertType(Type type) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TYPE_SQL)) {

            preparedStatement.setString(1, type.getType_name());
            preparedStatement.setString(2, type.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_TYPES)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type_name = resultSet.getString("type_name");
                String status = resultSet.getString("status");

                Type type = new Type(id, type_name, status);
                types.add(type);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return types;
    }

    public Type getTypeById(int typeId) {
        Type type = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYPE_BY_ID)) {

            preparedStatement.setInt(1, typeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String type_name = resultSet.getString("type_name");
                    String status = resultSet.getString("status");

                    type = new Type(id, type_name, status);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return type;
    }

    public void updateType(Type type) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TYPE_SQL)) {

            preparedStatement.setString(1, type.getType_name());
            preparedStatement.setString(2, type.getStatus());
            preparedStatement.setInt(3, type.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public void deleteType(int typeId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TYPE_SQL)) {

            preparedStatement.setInt(1, typeId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }
}
