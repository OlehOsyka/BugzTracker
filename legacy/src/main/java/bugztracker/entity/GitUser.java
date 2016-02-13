package bugztracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Author: Y. Vovk
 * 12.02.16.
 */
@Entity
@Table(name = "git_project_creds")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitUser {

    private String email;
    private String password;
    private Project projectGit;
    private String repositoryName;
    private String nick;

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project_id",
            referencedColumnName = "id",
            nullable = false)
    public Project getProjectGit() {
        return projectGit;
    }

    public void setProjectGit(Project projectGit) {
        this.projectGit = projectGit;
    }

    @Id
    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @Column(nullable = false)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GitUser gitUser = (GitUser) o;

        return new EqualsBuilder()
                .append(repositoryName, gitUser.repositoryName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(repositoryName)
                .toHashCode();
    }
}