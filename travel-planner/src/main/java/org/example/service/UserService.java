package org.example.service;

import org.example.models.User;

import java.sql.SQLException;

public interface UserService {
    void save(User user) throws SQLException;
    boolean authenticate(String username, String password) throws SQLException;
}


