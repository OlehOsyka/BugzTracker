package com.bugztracker.commons.entity.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 16:17
 */
public class RepositoryInfo {

    private String gitEmail;
    private String gitPassword;
    private String gitName;
    private String repositoryUrl;

    public String getGitEmail() {
        return gitEmail;
    }

    public void setGitEmail(String gitEmail) {
        this.gitEmail = gitEmail;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public void setGitPassword(String gitPassword) {
        this.gitPassword = gitPassword;
    }

    public String getGitName() {
        return gitName;
    }

    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RepositoryInfo that = (RepositoryInfo) o;

        return new EqualsBuilder()
                .append(gitEmail, that.gitEmail)
                .append(gitPassword, that.gitPassword)
                .append(gitName, that.gitName)
                .append(repositoryUrl, that.repositoryUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(gitEmail)
                .append(gitPassword)
                .append(gitName)
                .append(repositoryUrl)
                .toHashCode();
    }
}
