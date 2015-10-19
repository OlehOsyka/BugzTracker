package bugztracker.controller;

import bugztracker.bean.LoginBean;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.exception.ValidationException;
import bugztracker.service.IUserService;
import bugztracker.util.MD5Encoder;
import bugztracker.validator.IValidator;
import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IValidator<User> userValidator;

    @Autowired
    private IValidator<LoginBean> loginBeanValidator;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginBean credentials, WebRequest request,
                                HttpSession session) {
        Map<String, String> response = new HashMap<>();

        loginBeanValidator.validate(credentials);

        User user = userService.find(credentials.getEmail());

        if (user == null) {
            response.put("error", "No user found with such login!");
        } else if (!MD5Encoder.encrypt(credentials.getPassword()).startsWith(user.getPassword())) {
            response.put("error", "Incorrect password! Please, check and try again!");
        }

        if (!response.isEmpty()) {
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        if (credentials.isRemember()) {
            if (user != null) {
                //now + two weeks
                user.setDateExpired(new Timestamp(DateTime.now().plusWeeks(2).getMillis()));
                //set session on two weeks
                session.setMaxInactiveInterval(Weeks.TWO.toStandardSeconds().getSeconds());
            }
            userService.update(user);
        }

        List<Long> projectIds = new ArrayList<>();
        for (Project pr : user.getProjects()) {
            projectIds.add(pr.getId());
        }

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("userProjectIds", projectIds, WebRequest.SCOPE_SESSION);
        response.put("redirect", "/dashboard");

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User newUser, WebRequest request,
                                   HttpSession session) {
        Map<String, String> response = new HashMap<>();

        userValidator.validate(newUser);

        User user = userService.find(newUser.getEmail());

        if (user != null) {
            response.put("error", "Email has already been registered! ");
        }

        if (!response.isEmpty()) {
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        newUser.setId(UUID.randomUUID().getMostSignificantBits());

        String passw = newUser.getPassword();
        newUser.setPassword(MD5Encoder.encrypt(passw).substring(0, 10));

        userService.add(newUser);

        request.setAttribute("user", newUser, WebRequest.SCOPE_SESSION);
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

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUsers(@RequestParam String query) {
        return userService.findAll(query);
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
