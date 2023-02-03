package com.firstservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login servlet testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "username", value = "bharat"),
                @WebInitParam(name = "password", value = "Bharat@123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get request parameters for username and password
        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        //get servlet config init params
        String userId = getServletConfig().getInitParameter("username");
        String pass = getServletConfig().getInitParameter("password");

        if (userId.equals(username) && pass.equals(password)) {
            req.setAttribute("username", username);
            req.getRequestDispatcher("loginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.write("<font color=red> User name or password is wrong!.</font>");
            requestDispatcher.include(req, resp);
            out.close();
        }
    }
}
