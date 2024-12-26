package org.example.service;

import org.example.models.User;
import org.example.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository = new UserRepository();

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(int userId) throws SQLException {
        userRepository.delete(userId);
    }

    @Override
    public List<User> searchUsers(String search) throws SQLException {
        return userRepository.findByUsernameOrRole(search);
    }
    @Override
    public void updateUserRole(int userId, String newRole) throws SQLException {
        userRepository.updateRole(userId, newRole);
    }

}


