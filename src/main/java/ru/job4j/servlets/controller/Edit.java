package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Validate;
import ru.job4j.servlets.repository.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class Edit extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("name");
        User user = validate.findById(Integer.valueOf(id));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = validate.findById(Integer.valueOf(id));
        User newUser = new User(user.getId(), name, login, user.getPassword(), email, new Role(user.getRole()), new Date(), user.getPhotoId());
        validate.update(newUser);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }
}
