package com.example.servlet;

import com.example.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet
public class LoginServlet extends HttpServlet {
    final String  LOGIN_JSP = "/login.jsp";
    final String HELLO_JSP = "/user/hello.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(LOGIN_JSP);
        } else {
            resp.sendRedirect(HELLO_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(password.trim().equals("")){
            RequestDispatcher rq = req.getRequestDispatcher(LOGIN_JSP);
            rq.forward(req, resp);
        }
        List<String> users = Users.getInstance().getUsers();
        for (String e : users) {
            if (e.equals(login)) {
                req.getSession().setAttribute("user", login);
            }
            if (req.getSession().getAttribute("user") != null) {
                RequestDispatcher rq = req.getRequestDispatcher(HELLO_JSP);
                rq.forward(req, resp);
            }
        }
        resp.sendRedirect(LOGIN_JSP);

    }
}
