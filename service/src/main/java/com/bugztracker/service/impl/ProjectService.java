package com.bugztracker.service.impl;

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
    public List<String> getProjectNames(String userEmail) {
        List<String> projectNames = new ArrayList<>();
        projectRepository.getProjects(userEmail).forEach(pr -> projectNames.add(pr.getName()));
        return projectNames;
    }
}
