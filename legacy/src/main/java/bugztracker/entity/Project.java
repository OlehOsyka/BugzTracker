package bugztracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Y. Vovk
 * 02.10.15.
 */
@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project implements Serializable {

    private int id;
    private String name;
    private Date date;
    private String description;
    private User userOwner;
    private boolean isArchived;
    private Set<User> participants = new HashSet<>();
    private Set<Issue> issues = new HashSet<>();
    private Set<IssueCommit> commits = new HashSet<>();
    private Set<GitUser> gitUsers = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    @NotBlank(message = "Name is required! ")
    @Size(max = 300, message = "Please, shorten the name of project. Not more than 300 symbols is possible! ")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "participant",
            joinColumns = {@JoinColumn(name = "project_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)})
    @Size(min = 1, message = "At least one participant is required! ")
    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    @OneToMany(mappedBy = "project")
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_owner_id",
            referencedColumnName = "id",
            nullable = false)
    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    @Column(name="is_active")
    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
        isArchived = archived;
    }

    @OneToMany(mappedBy = "projectCommit")
    public Set<IssueCommit> getCommits() {
        return commits;
    }

    public void setCommits(Set<IssueCommit> commits) {
        this.commits = commits;
    }

    @OneToMany(mappedBy = "projectGit")
    public Set<GitUser> getGitUsers() {
        return gitUsers;
    }

    public void setGitUsers(Set<GitUser> gitUsers) {
        this.gitUsers = gitUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return new EqualsBuilder()
                .append(id, project.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("date", date)
                .append("description", description)
                .append("isArchived", isArchived)
                .toString();
    }
}
