package com.bugztracker.web.helpers;

import com.bugztracker.commons.entity.user.User;
import org.apache.commons.codec.digest.DigestUtils;
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
            return response;
        }

        User credentialsUser = actual.get();
        String md5Hash = DigestUtils.md5Hex(credentialsUser.getPassword());
        if (!md5Hash.startsWith(credentials.getPassword())) {
            response.add(VIEW_ERROR, "Incorrect password! Please, check and try again!");
            return response;
        }

        if (!credentialsUser.isActive()) {
            response.add(VIEW_ERROR, "Your account has not been activated!");
            return response;
        }

        response.add("redirect", "/main");
        return response;
    }

    public Response register(Optional<User> foundUser, User newUser, boolean isRegisterToken, int sessionTime) {
        Response response = new Response();
        if (foundUser.isPresent() && !foundUser.get().equals(newUser)) {
            response.add(VIEW_ERROR, "Email has already been registered! ");
            return response;
        }

        if (foundUser.isPresent() && foundUser.get().equals(newUser)) {
            response.add(VIEW_ERROR, "Registration link has already been sent to you! ");
            return response;
        }

        String md5Hash = DigestUtils.md5Hex(newUser.getPassword());
        newUser.setPassword(md5Hash.substring(0, 10));
        if (isRegisterToken) {
            newUser.setActive(false);
            newUser.setDueRegisterDate(DateTime.now().plusMinutes(sessionTime).toDate());

            String registrationToken = UUID.randomUUID().toString().substring(0, 15);
            newUser.setRegistrationToken(registrationToken);

            response.add(VIEW_SUCCESS, "Email with activation link has been sent! Please, follow it to succeed in signing up!");
        } else {
            newUser.setActive(true);
            newUser.setRegistrationToken(null);
            newUser.setDueRegisterDate(null);
        }
        response.add(USER_TO_REGISTER, newUser);
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
