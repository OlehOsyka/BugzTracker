package bugztracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Author: Y. Vovk
 * 12.02.16.
 */
@Entity
@Table(name = "issue_commit")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueCommit implements Serializable {

    private Issue issueCommit;
    private String commit;
    private Timestamp date;
    private Project projectCommit;

    @Id
    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    @Column(nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "issue_id",
            referencedColumnName = "id", nullable = false)
    @NotNull(message = "Issue to commit is required! ")
    public Issue getIssueCommit() {
        return issueCommit;
    }

    public void setIssueCommit(Issue issueCommit) {
        this.issueCommit = issueCommit;
    }

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project_id",
            referencedColumnName = "id", nullable = false)
    @NotNull(message = "Project to commit is required! ")
    public Project getProjectCommit() {
        return projectCommit;
    }

    public void setProjectCommit(Project projectCommit) {
        this.projectCommit = projectCommit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        IssueCommit that = (IssueCommit) o;

        return new EqualsBuilder()
                .append(commit, that.commit)
                .append(projectCommit, that.projectCommit)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(commit)
                .append(projectCommit)
                .toHashCode();
    }

}