package bugztracker.service;

import bugztracker.entity.IssueComment;
import bugztracker.entity.User;

/**
 * Created by Y. Vovk on 04.11.15.
 */
public interface IIssueCommentService  extends IService<IssueComment>{

    void addComment(IssueComment comment, User sender);
    void updateComment(IssueComment comment);
}
