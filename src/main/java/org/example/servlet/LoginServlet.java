package org.example.servlet;

import org.example.models.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp); // Отображение страницы логина
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userService.authenticateAndGetUser(username, password);

            if (user != null) {
                HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();  // Invalidate previous session to prevent session fixation
                }
                session = req.getSession(true);  // Create a new session
                session.setAttribute("user", user);

                // Secure the session with HttpOnly and Secure flags if using HTTPS
                resp.setHeader("Set-Cookie", "JSESSIONID=" + session.getId() + "; HttpOnly; Secure");

                if ("admin".equals(user.getRole())) {
                    resp.sendRedirect("/admin");
                } else {
                    resp.sendRedirect("/catalog");
                }
            } else {
                req.setAttribute("error", "Invalid credentials. Please try again.");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            // Log the error for debugging purposes
            e.printStackTrace();
            throw new ServletException("Error authenticating user", e);
        }
    }
}
