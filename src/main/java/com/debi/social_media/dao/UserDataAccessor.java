package com.debi.social_media.dao;

import com.debi.social_media.DatabaseConnection;
import com.debi.social_media.enums.Gender;
import com.debi.social_media.exceptions.UserNotFoundException;
import com.debi.social_media.models.User;
import com.debi.social_media.utils.PasswordHasher;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataAccessor {
    Connection connection;

    public UserDataAccessor() throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getConnection();
    }
    public void Shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void CreateUser(User user) {
        String sql = "INSERT INTO USERS(username, password, email, bio, gender, country) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, PasswordHasher.hashPassword(user.getPassword()));
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getBiography());
            stmt.setString(5, user.getGender() == Gender.MALE? "M" : "F");
            stmt.setString(6, user.getCountry());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public User FindUserByUsername(String username) throws UserNotFoundException, SQLException {
        String sql = "SELECT id, username, password, email, bio, gender, country FROM USERS WHERE username = ? LIMIT 1";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setBiography(rs.getString("bio"));
            user.setGender(rs.getString("gender").equals("M") ? Gender.MALE : Gender.FEMALE);
            user.setCountry(rs.getString("country"));
            return user;
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }
}
