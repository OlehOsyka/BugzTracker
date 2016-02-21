package com.bugztracker.service.impl;

import com.bugztracker.commons.entity.user.User;
import com.bugztracker.persistence.dao.IProjectRepository;
import com.bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:15
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public List<String> getProjectNames(User user) {
        List<String> projectNames = new ArrayList<>();
        projectRepository.getProjects(user.getEmail()).forEach(pr -> projectNames.add(pr.getName()));
        return projectNames;
    }
}
