package bugztracker.service.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.IIssueRepository;
import bugztracker.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Y. Vovk on 16.10.15.
 */
@Service
@Transactional
public class IssueService implements IIssueService {

    @Autowired
    private IIssueRepository issueRepository;

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
}
