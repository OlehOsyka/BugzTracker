package bugztracker.service;

import bugztracker.bean.Mail;
import org.apache.velocity.VelocityContext;

/**
 * Created by Y. Vovk on 05.11.15.
 */
public interface IEmailService {

    void sendEmail(Mail mail, VelocityContext velocityContext);
}
