package bugztracker.repository.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IIssueRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Y. Vovk on 16.10.15.
 */
@Repository
public class IssueRepository extends AbstractRepository<Issue> implements IIssueRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public IssueRepository() {
        super(Issue.class);
    }

    @Override
    public List<Issue> getByProject(Project project) {
        return (List<Issue>) sessionFactory.getCurrentSession().createCriteria(Issue.class)
                .createAlias("project", "pr").add(Restrictions.eq("pr.id", project.getId())).list();
    }

    @Override
    public List<Issue> getByProjectAndUser(Project project, User user) {
        return null;
    }
}