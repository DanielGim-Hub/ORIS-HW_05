package org.example.servlet;

import org.example.models.User;
import org.example.service.AdminService;
import org.example.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !"admin".equals(((User) session.getAttribute("user")).getRole())) {
            resp.sendRedirect("/login");
            return;
        }

        String search = req.getParameter("search");
        try {
            List<User> users;
            if (search != null && !search.isEmpty()) {
                users = adminService.searchUsers(search);
            } else {
                users = adminService.getAllUsers();
            }
            req.setAttribute("users", users);
            req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error retrieving users", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("delete".equals(action)) {
                int userId = Integer.parseInt(req.getParameter("userId"));
                adminService.deleteUserById(userId); // Удаляем пользователя по ID
            } else if ("updateRole".equals(action)) {
                int userId = Integer.parseInt(req.getParameter("userId"));
                String newRole = req.getParameter("role");
                User currentUser = (User) req.getSession().getAttribute("user");

                if ("admin".equals(newRole) && currentUser.getId() == userId) {
                    req.setAttribute("error", "You cannot change your own role to admin.");
                } else {
                    adminService.updateUserRole(userId, newRole);
                }
            }
            resp.sendRedirect("/admin");
        } catch (Exception e) {
            throw new ServletException("Error performing admin action", e);
        }
    }
}
