package bugztracker.repository;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;

import java.util.List;

/**
 * Created by Y. Vovk
 * Date: 16.10.15
 * Time: 1:20
 */
public interface IIssueRepository extends IRepository<Issue> {

    List<Issue> getByProject(Project project);
    List<Issue> getByProjectAndUser(Project project, User user);
}
