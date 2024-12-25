package org.example.service;

import org.example.models.User;
import org.example.repositories.UserRepository;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepository();
    @Override
    public void save(User user) throws SQLException {
        userRepository.save(user);
    }
    public boolean authenticate(String username, String password) throws SQLException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }
}
