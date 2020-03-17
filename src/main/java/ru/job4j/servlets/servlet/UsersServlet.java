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

public class UsersServlet extends HttpServlet {
    private Dispatcher dispatcher = new Dispatcher();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder builder = new StringBuilder("<table>");
        for (User user : dispatcher.findAll()) {
            builder.append("<tr><td>" + user + "</td>" +
                    "<td>" +
                    "<form action=' " + req.getContextPath() + "/update' method='get'>" +
                    "<input type='hidden' name='id' value='"+ user.getId() +"'/>" +
                    "<button>EDIT</button>" +
                    "</form>" +
                    "</td>" +
                    "<td>" +
                    "<form action=' " + req.getContextPath() + "/users' method='post'>" +
                    "<input type='hidden' name='id' value='"+ user.getId() +"'/>" +
                    "<button>DELETE</button>" +
                    "</form>" +
                    "</td></tr>");
        }
        builder.append("</table>");
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>UsersServlet</title>" +
                "</head>" +
                "<body>" +
                builder.toString() +
                "</body>" +
                "</html>");
        writer.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        dispatcher.delete(dispatcher.findById(Integer.parseInt(req.getParameter("id"))));
    }
}
