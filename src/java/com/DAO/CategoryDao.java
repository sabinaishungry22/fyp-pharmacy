package com.DAO;

import com.Model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    private static final String INSERT_CATEGORY_SQL = "INSERT INTO categories (category_name, status) VALUES (?, ?)";
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String UPDATE_CATEGORY_SQL = "UPDATE categories SET category_name = ?, status = ? WHERE id = ?";
    private static final String DELETE_CATEGORY_SQL = "DELETE FROM categories WHERE id = ?";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";

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

    public void insertCategory(Category category) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {

            preparedStatement.setString(1, category.getCategory_name());
            preparedStatement.setString(2, category.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CATEGORIES)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category_name = resultSet.getString("category_name");
                String status = resultSet.getString("status");

                Category category = new Category(id, category_name, status);
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return categories;
    }

    public Category getCategoryById(int categoryId) {
        Category category = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String category_name = resultSet.getString("category_name");
                    String status = resultSet.getString("status");

                    category = new Category(id, category_name, status);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }

        return category;
    }

    public void updateCategory(Category category) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL)) {

            preparedStatement.setString(1, category.getCategory_name());
            preparedStatement.setString(2, category.getStatus());
            preparedStatement.setInt(3, category.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }

    public void deleteCategory(int categoryId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL)) {

            preparedStatement.setInt(1, categoryId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
        }
    }
}
