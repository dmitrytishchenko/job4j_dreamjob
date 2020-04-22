package ru.job4j.servlets.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Dispatcher;
import ru.job4j.servlets.repository.Validate;
import ru.job4j.servlets.repository.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Delete extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("name"));
        User user = validate.findById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/views/DeleteUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        User user = validate.findById(id);
        validate.delete(user);
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
