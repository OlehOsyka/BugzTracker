package bugztracker.service;

import bugztracker.entity.IssueComment;
import bugztracker.entity.User;
import bugztracker.exception.service.IssueCommentServiceException;

import java.util.List;

/**
 * Created by Y. Vovk on 04.11.15.
 */
public interface IIssueCommentService  extends IService<IssueComment>{

    void addComment(IssueComment comment, User sender) throws IssueCommentServiceException;
    void updateComment(IssueComment comment) throws IssueCommentServiceException;

    List<IssueComment> getAll(int issueId);

    void delete(int issueId, int commentId) throws IssueCommentServiceException;
}
