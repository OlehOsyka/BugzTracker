package bugztracker.entity;

import javax.persistence.*;

/**
 * Created by Y. Vovk on 02.10.15.
 */
@Entity
@Table(name = "issue_attachment")
public class IssueAttachment {

    private long id;
    private String attachmentPath;
    private Issue issueByIssueId;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "issue_id", referencedColumnName = "id", nullable = false)
    public Issue getIssueByIssueId() {
        return issueByIssueId;
    }

    public void setIssueByIssueId(Issue issueByIssueId) {
        this.issueByIssueId = issueByIssueId;
    }

    @Column(name = "attachment_path", nullable = false)
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IssueAttachment that = (IssueAttachment) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "IssueAttachment{" +
                "id=" + id +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", issueByIssueId=" + issueByIssueId +
                '}';
    }

}
