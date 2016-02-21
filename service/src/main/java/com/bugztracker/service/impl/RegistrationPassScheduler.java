package com.bugztracker.service.impl;

import com.bugztracker.service.IUserService;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Y. Vovk
 * Date: 06.11.15
 * Time: 22:26
 */
public class RegistrationPassScheduler {

    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "${cron.refresh}")
    private void refreshAuthentication() {
        userService.removeUsersWithRegistrationDatePassed();
    }
}
