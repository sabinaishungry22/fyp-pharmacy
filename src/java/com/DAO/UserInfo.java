package com.DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.Model.User;

public class UserInfo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public User login(User u) {
        User loggedInUser = null;
        String sql = "SELECT * FROM usersignup WHERE username = ? AND password = ? AND role = ?";
        
        try (Connection con = getConnection(); 
             PreparedStatement statement = con.prepareStatement(sql)) {
            
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getPassword());
            statement.setString(3, u.getRole());
            
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    loggedInUser = new User();
                    loggedInUser.setId(result.getInt("userID")); // Ensure ID is set
                    loggedInUser.setUsername(result.getString("username"));
                    loggedInUser.setPassword(result.getString("password"));
                    loggedInUser.setRole(result.getString("role"));
                    loggedInUser.setEmail(result.getString("email"));
                    loggedInUser.setPhone(result.getString("phone"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loggedInUser;
    }

    public int signup(User u) {
        int status = 0;
        String sql = "INSERT INTO usersignup (username, password, role, email, phone) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection(); 
             PreparedStatement statement = con.prepareStatement(sql)) {
            
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getPassword());
            statement.setString(3, u.getRole());
            statement.setString(4, u.getEmail());
            statement.setString(5, u.getPhone());
            
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean updateUser(User user) {
        boolean updateSuccess = false;
        String sql = "UPDATE usersignup SET username = ?, password = ?, email = ?, phone = ? WHERE userID = ?";
        
        try (Connection con = getConnection(); 
             PreparedStatement statement = con.prepareStatement(sql)) {
            
            Logger.getLogger(UserInfo.class.getName()).log(Level.INFO, "Updating user with ID: {0}", user.getId());
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getId());
            
            int rowsAffected = statement.executeUpdate();
            Logger.getLogger(UserInfo.class.getName()).log(Level.INFO, "User update rows affected: {0}", rowsAffected);
            updateSuccess = rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateSuccess;
    }
}
