package ru.job4j.servlets.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class Edit extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Edit.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("name");
        LOG.trace("id = " + id);
        User user = Dispatcher.getDispatcher().findById(Integer.valueOf(id));
        LOG.trace("user = " + user.getName());
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        User user = Dispatcher.getDispatcher().findById(Integer.valueOf(id));
        User newUser = new User(user.getId(), name, login, user.getPassword(), email, new Role(user.getRole()), new Date(), user.getPhotoId());
        Dispatcher.getDispatcher().update(newUser);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }
}
