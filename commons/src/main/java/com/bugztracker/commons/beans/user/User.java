package com.bugztracker.commons.beans.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Id
    private int id;

    @NotBlank(message = "Full name is required! ")
    @Size(max = 100, message = "Please, shorten your full name to 100 symbols! ")
    private String fullName;

    @NotBlank(message = "Password is required! ")
    @Size(min = 6, message = "Password must be more than 6 symbols length! ")
    private String password;

    @NotBlank(message = "Email is required! ")
    @Size(max = 50, message = "Please, use email 50 symbols length! ")
    @Email(message = "Email is not valid! ")
    private String email;

    private Date dateExpired;
    private Date dueRegisterDate;
    private boolean isActive;
    private String registrationToken;
    private List<Participation> projects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Date getDueRegisterDate() {
        return dueRegisterDate;
    }

    public void setDueRegisterDate(Date dueRegisterDate) {
        this.dueRegisterDate = dueRegisterDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public List<Participation> getProjects() {
        return projects;
    }

    public void setProjects(List<Participation> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(isActive, user.isActive)
                .append(fullName, user.fullName)
                .append(password, user.password)
                .append(email, user.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fullName)
                .append(password)
                .append(email)
                .append(isActive)
                .toHashCode();
    }
}
