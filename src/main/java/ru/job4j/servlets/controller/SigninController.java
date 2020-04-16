package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (Dispatcher.getDispatcher().isCredential(login, password)) {
            HttpSession session = req.getSession();
                session.setAttribute("login", login);
                User user = Dispatcher.getDispatcher().getRole(login, password);
                req.setAttribute("user", user);
                String role = user.getRole();
                goToMenu(req, resp, role);
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }

    public void goToMenu(HttpServletRequest req, HttpServletResponse resp, String role) throws ServletException, IOException {
        if (role.equals("admin")) {
            req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req, resp);
        } else if (role.equals("user")) {
            req.getRequestDispatcher("WEB-INF/views/UserRoleView.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/views/GuestRoleView.jsp").forward(req, resp);
        }
    }
}
