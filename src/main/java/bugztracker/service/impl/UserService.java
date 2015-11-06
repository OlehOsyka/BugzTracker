package bugztracker.service.impl;


import bugztracker.bean.LoginBean;
import bugztracker.bean.Mail;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.IUserRepository;
import bugztracker.service.IEmailService;
import bugztracker.service.IUserService;
import bugztracker.util.MD5Encoder;
import org.apache.velocity.VelocityContext;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Y. Vovk on 17.09.15.
 */
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IEmailService emailService;

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void add(User entity) {
        userRepository.add(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void update(User entity) {
        userRepository.update(entity);
    }

    @Override
    public User find(String email) {
        return userRepository.find(email);
    }

    @Override
    public List<User> findAll(String query) {
        return userRepository.findAll(query);
    }

    @Override
    public List<User> findByIds(List<Integer> ids) {
        return userRepository.findById(ids);
    }

    @Override
    public Map<String, String> login(User user, LoginBean loginBean) {
        Map<String, String> response = new HashMap<>();

        if (user == null) {
            response.put("error", "No user found with such login!");
        } else if (!MD5Encoder.encrypt(loginBean.getPassword()).startsWith(user.getPassword())) {
            response.put("error", "Incorrect password! Please, check and try again!");
        } else {
            if (!user.getIsActive()) {
                response.put("error", "Your account has not been activated!");
            } else {
                response.put("redirect", "/dashboard");
                if (loginBean.isRemember()) {
                    //now + two weeks
                    user.setDateExpired(new Timestamp(DateTime.now().plusWeeks(2).getMillis()));
                    //set session on two weeks
                    userRepository.update(user);
                }
            }
        }
        return response;
    }

    @Override
    public Map<String, String> register(User newUser) {
        Map<String, String> response = new HashMap<>();
        User user = find(newUser.getEmail());

        if (user != null) {
            response.put("error", "Email has already been registered! ");
        } else {
            newUser.setId((int) UUID.randomUUID().getMostSignificantBits());
            newUser.setPassword(MD5Encoder.encrypt(newUser.getPassword()).substring(0, 10));
            newUser.setIsActive(false);
            newUser.setDueRegisterDate(new Timestamp(DateTime.now().plusMinutes(30).getMillis()));

            String registrationToken = UUID.randomUUID().toString().substring(0, 15);
            newUser.setRegistrationToken(registrationToken);

            userRepository.add(newUser);

            Mail mail = new Mail();
            mail.setMailFrom("smyulia96@gmail.com");
            mail.setMailTo(newUser.getEmail());
            mail.setMailSubject("Activation link for Bugzz Tracker");
            mail.setTemplateName("confirmation-template.vm");


            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("fullName", newUser.getFullName());
            velocityContext.put("link", "http://109.86.228.225:8080/account/activation?token="+registrationToken);

            emailService.sendEmail(mail, velocityContext);

            response.put("message", "Email with activation link has been sent! Please, follow it to succeed in signing up!");
        }

        return response;
    }

    @Override
    public List<Integer> getProjectsIdsOfUser(User user) {
        List<Integer> projectIds = new ArrayList<>();
        for (Project pr : user.getProjects()) {
            projectIds.add(pr.getId());
        }
        return projectIds;
    }

    @Override
    public List<User> getUsersByProjectId(int id, String query) {
        return userRepository.getUsersByProjectId(id, query);
    }

    @Override
    public Map<String, String> activateAccount(String registrationToken) {
        User user = userRepository.getUserByRegistrationToken(registrationToken);
        Map<String, String> response = new HashMap<>();

        if (user == null || new Timestamp(DateTime.now().getMillis()).after(user.getDueRegisterDate())) {
            response.put("error", "Activation link has been disabled. Please, sign up again to get new activation link.");
        } else {
            user.setIsActive(true);
            user.setRegistrationToken(null);
            user.setDueRegisterDate(null);
            userRepository.update(user);

            response.put("approve", "Your account has been activated! You can ");
        }

        return response;
    }

    @Override
    public void removeUsersWithRegistrationDatePassed(Date date) {
        userRepository.removeUsersWithRegistrationDatePassed(date);
    }
}
