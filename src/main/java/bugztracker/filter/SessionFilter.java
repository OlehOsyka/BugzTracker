package bugztracker.filter;

import bugztracker.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

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
            //now + two weeks
            user.setDateExpired(new Timestamp(System.currentTimeMillis() + 1209600000L));
            //update user
            //set session on two weeks
            session.setMaxInactiveInterval(1209600000);
        } else {
            user.setDateExpired(null);
            //update user
            requestDispatcher.forward(request, response);
        }

        //process request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //no op
    }
}
