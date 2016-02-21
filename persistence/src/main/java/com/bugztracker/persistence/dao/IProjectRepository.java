package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.project.Project;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IProjectRepository extends IBaseDao<Project> {

    Project findByProjectName(String name);
    List<Project> getProjects(String email);

}
