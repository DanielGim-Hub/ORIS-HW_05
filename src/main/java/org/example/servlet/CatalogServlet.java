package org.example.servlet;

import org.example.models.Place;
import org.example.service.CatalogService;
import org.example.service.CatalogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    private final CatalogService catalogService = new CatalogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Если пользователь не авторизован, перенаправляем на страницу логина
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("/login");
            return;
        }

        try {
            List<Place> places = catalogService.getAllPlaces();
            req.setAttribute("places", places);
            req.getRequestDispatcher("/jsp/catalog.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving catalog data", e);
        }
    }
}

