package bugztracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 0:01
 */
@Entity
@Table(name = "issue")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue implements Serializable {

    private int id;
    private String name;
    private Date date;
    private Date lastUpdate;
    private String priority;
    private String status;
    private String description;
    private String category;
    private BigDecimal version;
    private User userCreator;
    private User assignee;
    private Project project;
    private Set<IssueAttachment> attachments = new HashSet<>();
    private Set<IssueComment> comments = new HashSet<>();
    private Set<IssueCommit> commits = new HashSet<>();

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
    @Size(max = 300, message = "Please, shorten the name of issue. Not more than 300 symbols is possible! ")
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

    @Column(name = "date_updated")
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Column(nullable = false)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Column(nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    @Digits(integer = 2, fraction = 1, message = "Version must be a float number from 1 to 10! ")
    @Range(min = 1, max = 10, message = "Version must be a float number from 1 to 10! ")
    @NotNull(message = "Version is required and must be a float number from 1 to 10!")
    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id_cr",
            referencedColumnName = "id",
            nullable = false)
    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id_as",
            referencedColumnName = "id",
            nullable = false)
    @NotNull(message = "Assignee is required! ")
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project_id",
            referencedColumnName = "id",
            nullable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @OneToMany(mappedBy = "issueByIssueId", cascade = CascadeType.ALL)
    public Set<IssueAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<IssueAttachment> attachments) {
        this.attachments = attachments;
    }

    @OneToMany(mappedBy = "issueByIssueId",  cascade = CascadeType.ALL)
    public Set<IssueComment> getComments() {
        return comments;
    }

    public void setComments(Set<IssueComment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "issueCommit")
    public Set<IssueCommit> getCommits() {
        return commits;
    }

    public void setCommits(Set<IssueCommit> commits) {
        this.commits = commits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        return new EqualsBuilder()
                .append(id, issue.id)
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
                .append("priority", priority)
                .append("status", status)
                .append("description", description)
                .append("category", category)
                .append("version", version)
                .toString();
    }
}
