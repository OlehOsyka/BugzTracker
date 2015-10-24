package bugztracker.service.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.IIssueRepository;
import bugztracker.repository.IProjectRepository;
import bugztracker.repository.IUserRepository;
import bugztracker.service.IIssueService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Y. Vovk on 16.10.15.
 */
@Service
@Transactional
public class IssueService implements IIssueService {

    @Autowired
    private IIssueRepository issueRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public Issue get(int id) {
        return issueRepository.get(id);
    }

    @Override
    public List<Issue> getAll() {
        return issueRepository.getAll();
    }

    @Override
    public void add(Issue entity) {
        issueRepository.add(entity);
    }

    @Override
    public void delete(Issue entity) {
        issueRepository.delete(entity);
    }

    @Override
    public void update(Issue entity) {
        issueRepository.update(entity);
    }

    @Override
    public List<Issue> getByProject(Project project) {
        return issueRepository.getByProject(project);
    }

    @Override
    public List<Issue> getByProjectAndUser(Project project, User user) {
        return issueRepository.getByProjectAndUser(project, user);
    }

    @Override
    public void addIssue(Issue issue, User user) {
        issue.setId((int) UUID.randomUUID().getMostSignificantBits());
        issue.setUserCreator(userRepository.get(user.getId()));
        issue.setDate(new Date(DateTime.now().getMillis()));
        issue.setAssignee(userRepository.get(issue.getAssignee().getId()));
        issue.setProject(projectRepository.get(issue.getProject().getId()));
        issue.setStatus(Issue.Status.OPENED);

        issueRepository.add(issue);
    }

    @Override
    public void updateIssue(Issue issue) {
        Issue existedIssue = issueRepository.get(issue.getId());

        existedIssue.setLastUpdate(new Date(DateTime.now().getMillis()));
        existedIssue.setAssignee(userRepository.get(issue.getAssignee().getId()));
        existedIssue.setStatus(issue.getStatus());
        existedIssue.setCategory(issue.getCategory());
        existedIssue.setPriority(issue.getPriority());
        existedIssue.setName(issue.getName());
        existedIssue.setDescription(issue.getDescription());
        existedIssue.setVersion(issue.getVersion());

        issueRepository.update(existedIssue);
    }
}
