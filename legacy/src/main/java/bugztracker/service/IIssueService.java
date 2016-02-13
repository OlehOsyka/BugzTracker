package bugztracker.service;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;

import java.util.List;

/**
 * Created by Y. Vovk
 * Date: 16.10.15
 * Time: 1:19
 */
public interface IIssueService extends IService<Issue> {

    List<Issue> getByProject(Project project);
    List<Issue> getByProjectAndUser(Project project, User user);
    void addIssue(Issue issue, User user);
    void updateIssue(Issue issue);
    Issue getFull(int id);
}
