package bugztracker.service;

import bugztracker.entity.IssueAttachment;

import java.util.List;

/**
 * Created by oleg
 * Date: 02.11.15
 * Time: 10:46
 */
public interface IIssueAttachmentService extends IService<IssueAttachment> {
    List<IssueAttachment> getAttachments(int issueId);

    IssueAttachment getAttachment(int issueId, String fileName);

    void addAttachment(IssueAttachment issueAttachment);
}
