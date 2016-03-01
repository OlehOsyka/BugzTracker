package com.bugztracker.web.controllers;

import com.bugztracker.commons.entity.user.User;
import com.bugztracker.commons.validators.ICommonsValidator;
import com.bugztracker.service.IEmailService;
import com.bugztracker.service.IUserService;
import com.bugztracker.web.helpers.Response;
import com.bugztracker.web.helpers.UserManageHelper;
import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

import static com.bugztracker.web.Constants.*;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
@Controller
@SessionAttributes(USER_SESSION)
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserManageHelper manageHelper;

    @Autowired
    @Qualifier("commonsEntityValidator")
    private ICommonsValidator userValidator;

    @Autowired
    private IEmailService emailService;

    @Value("${web.is.register.token:false}")
    private boolean isRegisterToken;

    @Value("${web.session.time:30}")
    private int sessionTime;

    @RequestMapping(value = LOGIN_PATH, method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User credentials, WebRequest request,
                                HttpSession session) {
        userValidator.validate(credentials);
        Optional<User> user = userService.find(credentials.getEmail());
        Response response = manageHelper.login(user, credentials);

        if (response.contains(VIEW_ERROR)) {
            return new ResponseEntity<Object>(response.getResponse(), HttpStatus.UNAUTHORIZED);
        }

        User userToLogin = user.get();
        if (credentials.isRemember()) {
            userToLogin.setDateExpired(DateTime.now().plusWeeks(2).toDate());
            userService.update(userToLogin);
        }

        session.setMaxInactiveInterval(Weeks.TWO.toStandardSeconds().getSeconds());
        request.setAttribute(USER_SESSION, userToLogin, WebRequest.SCOPE_SESSION);
        return new ResponseEntity<Object>(response.getResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User newUser) {
        userValidator.validate(newUser);
        Optional<User> user = userService.find(newUser.getEmail());
        Response response = manageHelper.register(user, newUser, isRegisterToken, sessionTime);

        if (response.contains(VIEW_ERROR)) {
            return new ResponseEntity<Object>(response.getResponse(), HttpStatus.BAD_REQUEST);
        }

        User userToRegister = (User) response.get(USER_TO_REGISTER);
        userService.create(userToRegister);

        if (isRegisterToken) {
            emailService.sendRegisterEmail(userToRegister);
        }

        return new ResponseEntity<Object>(response.getResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = LOGOUT_PATH, method = RequestMethod.GET)
    public String logout(WebRequest request, SessionStatus status) {
        status.setComplete();
        User user = (User) request.getAttribute(USER_SESSION, WebRequest.SCOPE_SESSION);
        user.setDateExpired(null);
        userService.update(user);
        request.removeAttribute(USER_SESSION, WebRequest.SCOPE_SESSION);

        return "redirect:/";
    }

    @ExceptionHandler
    public ResponseEntity handleException(ValidationException exc) {
        Response response = new Response();
        response.add(VIEW_ERROR, exc.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = ACTIVATION_PATH, method = RequestMethod.GET, params = {ACTIVATION_TOKEN})
    public ModelAndView activateAccount(@RequestParam(ACTIVATION_TOKEN) String registrationToken) {
        Optional<User> userByToken = userService.getByRegistrationToken(registrationToken);
        Response response = manageHelper.activateAccount(userByToken);

        if (response.contains(USER_TO_ACTIVATE)) {
            userService.update((User) response.get(USER_TO_ACTIVATE));
        }

        return new ModelAndView(RESULT_PATH, response.getResponse());
    }

    @RequestMapping(value = "{projectName}/participants", method = RequestMethod.GET)
    public ResponseEntity getParticipantsOfProject(@PathVariable String projectName) {
        List<User> userList = userService.getByProject(projectName);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


}
