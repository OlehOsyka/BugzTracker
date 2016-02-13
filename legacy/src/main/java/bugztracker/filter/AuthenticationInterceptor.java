package bugztracker.filter;

import bugztracker.entity.User;
import bugztracker.service.IUserService;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by Y. Vovk on 03.10.15.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //no op
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int sessionTimeout = session.getMaxInactiveInterval();

        // not logged in
        if (user == null) {
            return;
        }

        Timestamp expirationDate = user.getDateExpired();

        if (expirationDate == null) {
            return;
        }

        long time = new Interval(DateTime.now().getMillis(), expirationDate.getTime()).toDurationMillis();
        if (time < sessionTimeout) {
            //update user
            user.setDateExpired(new Timestamp(DateTime.now().plusWeeks(2).getMillis()));
            userService.update(user);
            //set session on two weeks
            session.setMaxInactiveInterval(Weeks.TWO.toStandardSeconds().getSeconds());
        } else {
            user.setDateExpired(null);
            userService.update(user);
        }
    }
}
