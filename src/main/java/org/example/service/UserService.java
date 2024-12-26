package org.example.service;

import org.example.models.User;
import java.sql.SQLException;

public interface UserService {
    void save(User user) throws SQLException;
    User authenticateAndGetUser(String username, String password) throws SQLException; // Возвращает пользователя
}
