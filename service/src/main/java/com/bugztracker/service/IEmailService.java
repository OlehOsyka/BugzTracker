package com.bugztracker.service;

import com.bugztracker.commons.bean.Mail;
import com.bugztracker.commons.entity.user.User;
import org.apache.velocity.VelocityContext;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IEmailService {

    void sendEmail(Mail mail, VelocityContext velocityContext);
    void sendRegisterEmail(User sendTo);

}
