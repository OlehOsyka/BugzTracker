package bugztracker.entity;

import bugztracker.entity.constant.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "issue")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue implements Serializable {

    private int id;
    private String name;
    private Date date;
    private Date lastUpdate;
    private Priority priority;
    private Status status;
    private String description;
    private Category category;
    private BigDecimal version;
    private User userCreator;
    private User assignee;
    private Project project;

    @Id
    @Column(nullable = false)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(nullable = false)
    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_cr",
            referencedColumnName = "id",
            nullable = false)
    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_as",
            referencedColumnName = "id",
            nullable = false)
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id",
            referencedColumnName = "id",
            nullable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum Priority implements IEnum {

        BLOCKER(1), CRITICAL(2), MAJOR(3), TRIVIAL(4), MINOR(5);

        private final static Priority[] values = values();
        private final int value;

        Priority(int v) {
            value = v;
        }

        public int value() {
            return value;
        }

        public static Priority fromValue(int typeCode) {
            for (Priority priority : values) {
                if (priority.value == typeCode) {
                    return priority;
                }
            }
            throw new IllegalArgumentException("Invalid status type code: " + typeCode);
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum Status implements IEnum {

        OPENED(1), INPROGRESS(2), RESOLVED(3), CLOSED(4);

        private final static Status[] values = values();
        private final int value;

        Status(int v) {
            value = v;
        }

        public int value() {
            return value;
        }

        public static Status fromValue(int typeCode) {
            for (Status status : values) {
                if (status.value == typeCode) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid status type code: " + typeCode);
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum Category implements IEnum {

        ISSUE(1), BUG(2), IMPROVEMENT(3);

        private final static Category[] values = Category.values();
        private final int value;

        Category(int v) {
            value = v;
        }

        public int value() {
            return value;
        }

        public static Category fromValue(int typeCode) {
            for (Category category : values) {
                if (category.value == typeCode) {
                    return category;
                }
            }
            throw new IllegalArgumentException("Invalid status type code: " + typeCode);
        }
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
