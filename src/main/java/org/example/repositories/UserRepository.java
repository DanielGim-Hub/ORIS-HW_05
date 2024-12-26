package org.example.repositories;

import org.example.models.User;
import org.example.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final Connection connection = ConnectionManager.getConnection();

    public User findByUsername(String username) throws SQLException {
        String query = "SELECT * FROM \"User\" WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role") // Извлечение роли пользователя
                    );
                }
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM \"User\"";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role") // Извлечение роли пользователя
                ));
            }
        }
        return users;
    }


    public void save(User user) throws SQLException {
        String query = "INSERT INTO \"User\" (username, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole()); // Сохранение роли
            stmt.executeUpdate();
        }
    }


    public void update(User user) throws SQLException {
        String query = "UPDATE \"User\" SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole()); // Обновление роли
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        String query = "DELETE FROM \"User\" WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public List<User> findByUsernameOrRole(String search) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM \"User\" WHERE username LIKE ? OR role LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String searchPattern = "%" + search + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role")
                    ));
                }
            }
        }
        return users;
    }
    public void updateRole(int userId, String newRole) throws SQLException {
        String query = "UPDATE \"User\" SET role = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newRole);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

}
