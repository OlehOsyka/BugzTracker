package com.bugztracker.commons.entity.user;

import com.bugztracker.commons.entity.project.ProjectRole;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 13:58
 */
public class Participation {

    private String projectName;
    private List<ProjectRole> roles;
    private RepositoryInfo repository;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ProjectRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ProjectRole> roles) {
        this.roles = roles;
    }

    public RepositoryInfo getRepository() {
        return repository;
    }

    public void setRepository(RepositoryInfo repository) {
        this.repository = repository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Participation that = (Participation) o;

        return new EqualsBuilder()
                .append(projectName, that.projectName)
                .append(roles, that.roles)
                .append(repository, that.repository)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(projectName)
                .append(roles)
                .append(repository)
                .toHashCode();
    }
}
