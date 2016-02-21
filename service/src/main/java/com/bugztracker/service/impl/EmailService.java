package com.bugztracker.service.impl;

import com.bugztracker.commons.bean.Mail;
import com.bugztracker.commons.entity.user.User;
import com.bugztracker.service.IEmailService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
@Component
public class EmailService implements IEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${host.port}")
    private String hostPost;

    @Value("${mail.sender}")
    private String mailSendFrom;

    @Override
    public void sendEmail(Mail mail, VelocityContext velocityContext) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());

        Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        message.setText(stringWriter.toString());

        mailSender.send(message);
    }

    @Override
    public void sendRegisterEmail(User sendTo) {
        Mail mail = new Mail();
        mail.setMailFrom(mailSendFrom);
        mail.setMailTo(sendTo.getEmail());
        mail.setMailSubject("Activation link for Bugzz Tracker");
        mail.setTemplateName("confirmation-template.vm");

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("fullName", sendTo.getFullName());
        velocityContext.put("link",  hostPost + "/account/activation?token=" + sendTo.getRegistrationToken());

        sendEmail(mail, velocityContext);
    }
}
