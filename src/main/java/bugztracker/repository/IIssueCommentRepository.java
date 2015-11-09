package bugztracker.repository;

import bugztracker.entity.IssueComment;

import java.util.List;

/**
 * Created by Y. Vovk on 04.11.15.
 */
public interface IIssueCommentRepository extends IRepository<IssueComment> {

    List<IssueComment> getCommentsOfIssue(int issueId);

}
