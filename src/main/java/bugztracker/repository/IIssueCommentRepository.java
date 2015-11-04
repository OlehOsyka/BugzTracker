package bugztracker.repository;

import bugztracker.entity.Issue;
import bugztracker.entity.IssueComment;

import java.util.List;

/**
 * Created by Y. Vovk on 04.11.15.
 */
public interface IIssueCommentRepository {

    List<IssueComment> getCommentsOfIssue(Issue issue);
}
