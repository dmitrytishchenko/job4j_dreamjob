package ru.job4j.servlets.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.repository.Dispatcher;
import ru.job4j.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class UsersController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", Dispatcher.getDispatcher().findAll());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Dispatcher.getDispatcher().createNewUser(new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                new Date()));
        LOG.debug("Добавлена запись, плюс вывод размер всего пользователей в базе", Dispatcher.getDispatcher().findAll().size());
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
