package bugztracker.repository;

import bugztracker.entity.IssueAttachment;

import java.util.List;

/**
 * Created by oleg
 * Date: 02.11.15
 * Time: 10:41
 */
public interface IIssueAttachmentRepository extends IRepository<IssueAttachment>{
    List<IssueAttachment> getAttachments(int issueId);

    IssueAttachment getAttachment(int issueId, String fileName);
}
