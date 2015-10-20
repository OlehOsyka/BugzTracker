package bugztracker.filter;

import bugztracker.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Y. Vovk on 20.10.15.
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        boolean isLoggedIn = user != null;
        if (request.getRequestURI().equals("/") || request.getRequestURI().equals("/login")) {
            if (isLoggedIn) {
                response.sendRedirect("/dashboard");
                return false;
            }
        } else {
            if (!isLoggedIn) {
                response.sendRedirect("/");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //no op
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //no op
    }
}
