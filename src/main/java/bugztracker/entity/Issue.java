package bugztracker.entity;

import bugztracker.entity.constant.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
public class Issue {

    private long id;
    private String name;
    private Date date;
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
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "user_id_cr", referencedColumnName = "id", nullable = false)
    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_as", referencedColumnName = "id", nullable = false)
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public enum Priority implements IEnum {

        BLOCKER(1), CRITICAL(2), MAJOR(3), TRIVIAL(4), MINOR(5);

        private final static Priority[] values = values();
        private final int value;

        Priority(int v) {
            value = v;
        }

        @JsonValue
        public int value() {
            return value;
        }

        @JsonCreator
        public static Priority fromValue(int typeCode) {
            for (Priority priority : values) {
                if (priority.value == typeCode) {
                    return priority;
                }
            }
            throw new IllegalArgumentException("Invalid status type code: " + typeCode);
        }
    }

    public enum Status implements IEnum {

        OPENED(1), INPROGRESS(2), RESOLVED(3), CLOSED(4);

        private final static Status[] values = values();
        private final int value;

        Status(int v) {
            value = v;
        }

        @JsonValue
        public int value() {
            return value;
        }

        @JsonCreator
        public static Status fromValue(int typeCode) {
            for (Status status : values) {
                if (status.value == typeCode) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid status type code: " + typeCode);
        }
    }

    public enum Category implements IEnum {

        ISSUE(1), BUG(2), IMPROVEMENT(3);

        private final static Category[] values = Category.values();
        private final int value;

        Category(int v) {
            value = v;
        }

        @JsonValue
        public int value() {
            return value;
        }

        @JsonCreator
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Issue issue = (Issue) o;

        return id == issue.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", priority=" + priority +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", version=" + version +
                ", userCreator=" + userCreator +
                ", assignee=" + assignee +
                ", project=" + project +
                '}';
    }

}
