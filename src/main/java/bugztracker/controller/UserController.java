package bugztracker.controller;

import bugztracker.bean.LoginBean;
import bugztracker.entity.User;
import bugztracker.exception.ValidationException;
import bugztracker.service.IUserService;
import bugztracker.util.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginBean credentials, WebRequest request) {
        Map<String, String> response = new HashMap<>();

        User user = userService.find(credentials);

        if (user == null) {
            response.put("error", "No user found with such login!");
        } else if (!MD5Encoder.encrypt(credentials.getPassword()).startsWith(user.getPassword())) {
            response.put("error", "Incorrect password! Please, check and try again!");
        }

        if (!response.isEmpty()) {
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        if(credentials.isRemember()) {
            if (user != null) {
                //now + two weeks
                user.setDateExpired(new Timestamp(System.currentTimeMillis() + 1209600000L));
            }
            userService.update(user);
        }
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        response.put("redirect", "/dashboard");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(WebRequest request, SessionStatus status) {
        status.setComplete();

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        user.setDateExpired(null);
        userService.update(user);

        request.removeAttribute("user", WebRequest.SCOPE_SESSION);

        return "redirect:/";
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity handleException(ValidationException exc) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
