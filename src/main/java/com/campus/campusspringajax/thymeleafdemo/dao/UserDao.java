package com.campus.campusspringajax.thymeleafdemo.dao;

import com.campus.campusspringajax.thymeleafdemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserDao {
    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    public static final String Q_ADD_USER = "INSERT INTO users (name, email) VALUES (?, ?)";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Q_ADD_USER)
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to add user: {}", e.getMessage());
        }
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Failed to get users: {}", e.getMessage());
        }

        return users;
    }

    public User getUserById(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            log.error("Failed to get user by id: {}", e.getMessage());
        }

        return user;
    }

    public void updateUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ?, email = ? WHERE id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to update user: {}", e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to delete user: {}", e.getMessage());
        }
    }
}