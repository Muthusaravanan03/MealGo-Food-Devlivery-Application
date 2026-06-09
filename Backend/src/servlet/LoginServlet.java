package servlet;

import db.Login;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get data from HTML form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Call your JDBC login method
        boolean result = Login.loginUser(email, password);

        // Set response type
        response.setContentType("text/html");

        if (result) {
            response.getWriter().println("✅ LOGIN SUCCESS");
        } else {
            response.getWriter().println("❌ INVALID LOGIN");
        }
    }
}