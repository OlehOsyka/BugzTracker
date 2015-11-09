package bugztracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "issue_comment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueComment implements Serializable {

    private int id;
    private String comment;
    private Timestamp date;
    private Issue issueByIssueId;
    private User sender;
    private Timestamp updateDate;

    @Id
    @Column(nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    @NotBlank(message = "Comment is required! ")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    @NotNull(message = "Issue to  be commented is required! ")
    public Issue getIssueByIssueId() {
        return issueByIssueId;
    }

    public void setIssueByIssueId(Issue issueByIssueId) {
        this.issueByIssueId = issueByIssueId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_sender",
            referencedColumnName = "id",
            nullable = false)
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Column(name = "update_date")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        IssueComment that = (IssueComment) o;

        return new EqualsBuilder()
                .append(id, that.id)
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
                .append("comment", comment)
                .append("date", date)
                .append("issueByIssueId", issueByIssueId)
                .append("sender", sender)
                .append("updateDate", updateDate)
                .toString();
    }
}
