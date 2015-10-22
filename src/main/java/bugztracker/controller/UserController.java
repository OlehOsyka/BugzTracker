package bugztracker.controller;

import bugztracker.bean.LoginBean;
import bugztracker.entity.User;
import bugztracker.exception.ValidationException;
import bugztracker.service.IUserService;
import bugztracker.validator.IValidator;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        loginBeanValidator.validate(credentials);

        User user = userService.find(credentials.getEmail());

        Map<String, String> response = userService.login(user, credentials);

        if (response.containsKey("error")) {
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        if (credentials.isRemember()) {
            //set session on two weeks
            session.setMaxInactiveInterval(Weeks.TWO.toStandardSeconds().getSeconds());
        }

        List<Integer> projectIds = userService.getProjectsIdsOfUser(user);

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("userProjectIds", projectIds, WebRequest.SCOPE_SESSION);

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User newUser, WebRequest request) {
        userValidator.validate(newUser);

        Map<String, String> response = userService.register(newUser);

        if (!response.isEmpty()) {
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        request.setAttribute("user", newUser, WebRequest.SCOPE_SESSION);

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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers(@RequestParam String query) {
        return userService.findAll(query);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, params = {"projectId", "query"})
    @ResponseBody
    public List<User> getUsersByProjectId(@RequestParam int projectId, @RequestParam String query) {
        List<User> users = userService.getUsersByProjectId(projectId, query);
        return users;
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
