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


public class UserCreateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserCreateServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>UserCreateServlet</title>" +
                "</head>" +
                "<body>" +
                "<form action=' " + req.getContextPath() + "/create' method='post'>" +
                "Name : <input type='text' name='name'/>" +
                "<br>" +
                "Login : <input type='text' login='login'/>" +
                "<br>" +
                "Email : <input type='text' email='email'/>" +
                "<br/>" +
                "<input type= 'submit'>" +
                "</form>" +
                "<br/>" +
                "</body>" +
                "</html>");
        writer.flush();
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
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
