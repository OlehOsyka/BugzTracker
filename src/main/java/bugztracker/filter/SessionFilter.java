package bugztracker.filter;

import bugztracker.entity.User;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

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
        RequestDispatcher logoutRequestDispatcher = req.getRequestDispatcher("/logout");
        User user = (User) session.getAttribute("user");
        int sessionTimeout = session.getMaxInactiveInterval();

        if (user == null) {
            logoutRequestDispatcher.forward(request, response);
        } else if (user.getDateExpired() != null && user.getDateExpired().getTime() - DateTime.now().getMillis() < sessionTimeout) {
            //now + two weeks
            //update user
            user.setDateExpired(new Timestamp(DateTime.now().plusWeeks(2).getMillis()));
            //set session on two weeks
            session.setMaxInactiveInterval(Weeks.TWO.toStandardSeconds().getSeconds());
        } else {
            user.setDateExpired(null);
            //update user
            logoutRequestDispatcher.forward(request, response);
        }

        //process request
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //no op
    }
}
