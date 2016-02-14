package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.project.Project;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
public interface IProjectRepository extends IBaseDao<Project> {

    Project findByProjectName(String name);

}
