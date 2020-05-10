package ru.job4j.servlets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.repository.DBStore;
import ru.job4j.servlets.repository.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddCityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=UTF-8");
        int id = Integer.valueOf(req.getParameter("name"));
        List<String> tasks = Dispatcher.getDispatcher().getCitiesFromCountry(id);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(tasks);
        final PrintWriter out = resp.getWriter();
        out.print(json);
        out.close();
        }
    }