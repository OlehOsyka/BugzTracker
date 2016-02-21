package com.bugztracker.web.helpers;

import com.bugztracker.commons.entity.user.User;
import com.bugztracker.web.util.MD5Encoder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static com.bugztracker.web.Constants.*;

/**
 * Author: Yuliia Vovk
 * Date: 19.02.16
 * Time: 16:38
 */
@Component
public class UserManageHelper {

    public Response login(Optional<User> actual, User credentials) {
        Response response = new Response();
        if (!actual.isPresent()) {
            response.add(VIEW_ERROR, "No user found with such login!");
        } else {
            User credentialsUser = actual.get();
            if (!MD5Encoder.encrypt(credentialsUser.getPassword()).startsWith(credentials.getPassword())) {
                response.add(VIEW_ERROR, "Incorrect password! Please, check and try again!");
            } else {
                if (!credentialsUser.isActive()) {
                    response.add(VIEW_ERROR, "Your account has not been activated!");
                } else {
                    response.add("redirect", "/main");
                }
            }
        }
        return response;
    }

    public Response register(Optional<User> foundUser, User credentials, boolean isRegisterToken, int sessionTime) {
        Response response = new Response();
        if (foundUser.isPresent() && !foundUser.get().equals(credentials)) {
            response.add(VIEW_ERROR, "Email has already been registered! ");
        } else if (foundUser.isPresent() && foundUser.get().equals(credentials)) {
            response.add(VIEW_ERROR, "Registration link has already been sent to you! ");
        } else {
            credentials.setPassword(MD5Encoder.encrypt(credentials.getPassword()).substring(0, 10));
            if (isRegisterToken) {
                credentials.setActive(false);
                credentials.setDueRegisterDate(new Timestamp(DateTime.now().plusMinutes(sessionTime).getMillis()));

                String registrationToken = UUID.randomUUID().toString().substring(0, 15);
                credentials.setRegistrationToken(registrationToken);

                response.add(VIEW_SUCCESS, "Email with activation link has been sent! Please, follow it to succeed in signing up!");
            } else {
                credentials.setActive(true);
                credentials.setRegistrationToken(null);
                credentials.setDueRegisterDate(null);
            }
            response.add(USER_TO_REGISTER, credentials);
        }
        return response;
    }

    public Response activateAccount(Optional<User> userByToken) {
        Response response = new Response();
        if (!userByToken.isPresent() || new Timestamp(DateTime.now().getMillis()).after(userByToken.get().getDueRegisterDate())) {
            response.add(VIEW_ERROR, "Activation has been already expired or is broken! ");
        } else {
            User user = userByToken.get();
            user.setActive(true);
            user.setRegistrationToken(null);
            user.setDueRegisterDate(null);

            response.add(USER_TO_ACTIVATE, user);
            response.add(VIEW_SUCCESS, "Your account has been activated! ");
        }
        return response;
    }

}
