package ru.job4j.servlets.servlet;

import ru.job4j.servlets.crud.Dispatcher;
import ru.job4j.servlets.crud.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserUpdateServlet extends HttpServlet {
    private Dispatcher dispatcher = new Dispatcher();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User modifyUser = null;
        if (req.getParameter("id") != null) {
            int id = Integer.valueOf(req.getParameter("id"));
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
                    "Login : <input type='text' login='login' value=" + modifyUser.getLogin() + ">" +
                    "<br>" +
                    "Email : <input type='text' email='email' value=" + modifyUser.getEmail() + ">" +
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
                    "<form action=' " + req.getContextPath() + "/update' method='post'>" +
                    "ID : <input type='text' id='id'>" +
                    "<br>" +
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
        User user = dispatcher.findById(Integer.valueOf(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        dispatcher.update(user);
        doGet(req, resp);
    }
}
