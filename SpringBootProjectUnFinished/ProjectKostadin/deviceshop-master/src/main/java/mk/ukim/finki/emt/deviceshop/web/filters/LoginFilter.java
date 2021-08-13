package mk.ukim.finki.emt.deviceshop.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String firstName = (String)request.getSession().getAttribute("firstName");
        String path = request.getServletPath();
        if (!path.equals("/login") && firstName==null) {
            response.sendRedirect("/login");
        }  else {
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
