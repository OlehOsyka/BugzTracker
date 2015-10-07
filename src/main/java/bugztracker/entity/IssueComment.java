package bugztracker.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "issue_comment")
public class IssueComment {

    private long id;
    private String comment;
    private Timestamp date;
    private Issue issueByIssueId;

    @Id
    @Column(nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
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
    @JoinColumn(name = "issue_id", referencedColumnName = "id", nullable = false)
    public Issue getIssueByIssueId() {
        return issueByIssueId;
    }

    public void setIssueByIssueId(Issue issueByIssueId) {
        this.issueByIssueId = issueByIssueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IssueComment that = (IssueComment) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "IssueComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", issueByIssueId=" + issueByIssueId +
                '}';
    }


}
