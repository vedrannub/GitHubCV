package mk.ukim.finki.emt.deviceshop.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="login servlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");

        out.println("<h1>");
        out.println("Login ");
        out.println("</h1>");

        out.println("<form action=\"/login\" method=\"POST\">");
        out.println("Name <input type=\"text\" name=\"firstName\"/> <br/>");
        out.println("Name <input type=\"submit\" value=\"Submit\"/> <br/>");
        out.println("</form>");


        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        req.getSession().setAttribute("firstName",firstName);
        resp.sendRedirect("/device/");
    }
}
