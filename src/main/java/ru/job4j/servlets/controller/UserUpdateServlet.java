package ru.job4j.servlets.controller;

import ru.job4j.servlets.repository.Dispatcher;
import ru.job4j.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private Dispatcher dispatcher = new Dispatcher();
    private User modifyUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            for (User user : dispatcher.findAll()) {
                if (user.getId() == id) {
                    modifyUser = user;
                }
            }
            writer.append("<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <title>UserUpdateServlet</title>" +
                    "</head>" +
                    "<body>" +
                    "<form action=' " + req.getContextPath() + "/update' method='post'>" +
                    "Name : <input type='text' name='name'value=" + modifyUser.getName() + ">" +
                    "<br>" +
                    "Login : <input type='text' name='login' value=" + modifyUser.getLogin() + ">" +
                    "<br>" +
                    "Email : <input type='text' name='email' value=" + modifyUser.getEmail() + ">" +
                    "<br>" +
                    "<input type= 'submit'>" +
                    "</form>" +
                    "<br/>" +
                    "</body>" +
                    "</html>");
        } else {
            writer.append("<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <title>UserUpdateServlet</title>" +
                    "</head>" +
                    "<body>" +
                    "<form action=' " + req.getContextPath() + "/update' method='get'>" +
                    "ID : <input type='text' name='id'>" +
                    "<input type= 'submit'>" +
                    "</form>" +
                    "<br/>" +
                    "</body>" +
                    "</html>");
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        modifyUser.setName(req.getParameter("name"));
        modifyUser.setLogin(req.getParameter("login"));
        modifyUser.setEmail(req.getParameter("email"));
        dispatcher.update(modifyUser);
        doGet(req, resp);
    }
}