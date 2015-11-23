package bugztracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oleg
 * Date: 19.11.15
 * Time: 9:13
 */
@Controller
public class HttpErrorHandler {

    @RequestMapping(value = "/404")
    public String error404() {
        return "errors/404";
    }

    @RequestMapping(value = "/500")
    public String error500() {
        return "errors/500";
    }
}
