package bugztracker.filter;

import bugztracker.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Y. Vovk on 03.10.15.
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //no op
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/logout");
        User user = (User) session.getAttribute("user");
        int sessionTimeout = session.getMaxInactiveInterval();
        if (user == null) {
            requestDispatcher.forward(request, response);
        } else if (user.getDateExpired() != null && user.getDateExpired().getTime() - System.currentTimeMillis() < sessionTimeout) {
                //to do
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //no op
    }
}
