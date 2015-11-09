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

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 0:00
 */
@Entity
@Table(name = "issue_attachment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueAttachment implements Serializable  {

    private int id;
    private String attachmentPath;
    private Issue issueByIssueId;

    @Id
    @Column(nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "issue_id",
            referencedColumnName = "id", nullable = false)
    @NotNull(message = "Issue to add a attachment to! ")
    public Issue getIssueByIssueId() {
        return issueByIssueId;
    }

    public void setIssueByIssueId(Issue issueByIssueId) {
        this.issueByIssueId = issueByIssueId;
    }

    @Column(name = "attachment_path", nullable = false)
    @NotBlank(message = "Path is required! ")
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        IssueAttachment that = (IssueAttachment) o;

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
                .append("attachmentPath", attachmentPath)
                .append("id", id)
                .toString();
    }
}
