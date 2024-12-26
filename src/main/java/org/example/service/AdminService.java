package org.example.service;

import org.example.models.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    List<User> getAllUsers() throws SQLException;
    void deleteUserById(int userId) throws SQLException;
    List<User> searchUsers(String search) throws SQLException;
    void updateUserRole(int userId, String newRole) throws SQLException;

}

