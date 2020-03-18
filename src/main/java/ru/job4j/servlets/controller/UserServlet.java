package ru.job4j.servlets.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    private Dispatcher dispatcher = new Dispatcher();
    private Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(dispatcher.findAll().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.dispatcher.work(req.getParameter("action"), new User(req.getParameter("name")));
        doGet(req, resp);
    }
}
