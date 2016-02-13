package bugztracker.service.impl;

import bugztracker.service.IUserService;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Y. Vovk
 * Date: 06.11.15
 * Time: 22:26
 */
public class Scheduler {

    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "${cron.refresh}")
    private void refreshAuthentication() {
        userService.removeUsersWithRegistrationDatePassed(DateTime.now().toDate());
    }
}
