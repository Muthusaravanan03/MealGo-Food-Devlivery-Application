package servlet;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        boolean success = Signup.registerUser(username, email, password, phone, address);

        if(success){
            response.sendRedirect("login.html");
        } else {
            response.getWriter().println("Signup failed");
        }
    }
}
