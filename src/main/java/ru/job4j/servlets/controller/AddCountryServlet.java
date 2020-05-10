package ru.job4j.servlets.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCountryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/jsonp");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"1\": \"Russia\", \"2\": \"USA\"}]");
        writer.flush();
    }
}
