package bugztracker.service.impl;

import bugztracker.bean.Mail;
import bugztracker.service.IEmailService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

/**
 * Created by Y. Vovk on 05.11.15.
 */
@Component
public class EmailService implements IEmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

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
}
