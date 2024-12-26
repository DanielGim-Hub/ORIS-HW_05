package org.example.servlet;

import org.example.models.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Перенаправляем пользователя на страницу регистрации
        resp.sendRedirect("/jsp/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметры из формы регистрации
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role"); // Новый параметр для роли пользователя

        // Создаем объект пользователя с указанной ролью
        User user = new User(username, email, password, role);

        try {
            // Сохраняем пользователя через сервис
            userService.save(user);
            // Перенаправляем на страницу успеха или логина
            resp.sendRedirect("/jsp/login.jsp");
        } catch (SQLException e) {
            // Обрабатываем исключение и возвращаем сообщение об ошибке
            req.setAttribute("error", "Ошибка регистрации: " + e.getMessage());
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }
    }
}
