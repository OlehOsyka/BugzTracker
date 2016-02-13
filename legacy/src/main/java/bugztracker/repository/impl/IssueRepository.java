package bugztracker.repository.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IIssueRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.FetchMode.JOIN;

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
                .setFetchMode("assignee", JOIN)
                .setFetchMode("userCreator", JOIN)
                .setFetchMode("project", JOIN)
                .createAlias("project", "pr")
                .add(Restrictions.eq("pr.id", project.getId()))
                .addOrder(Order.asc("priority"))
                .list();
    }

    @Override
    public List<Issue> getByProjectAndUser(Project project, User user) {
        return (List<Issue>) sessionFactory.getCurrentSession().createCriteria(Issue.class)
                .setFetchMode("assignee", JOIN)
                .setFetchMode("userCreator", JOIN)
                .setFetchMode("project", JOIN)
                .createAlias("project", "pr")
                .createAlias("assignee", "as")
                .add(Restrictions.eq("pr.id", project.getId()))
                .add(Restrictions.eq("as.id", user.getId()))
                .addOrder(Order.asc("priority"))
                .list();
    }

    @Override
    public Issue getFull(int id) {
        return (Issue) sessionFactory.getCurrentSession().createCriteria(Issue.class)
                .setFetchMode("attachments", JOIN)
                .setFetchMode("comments", JOIN)
                .setFetchMode("assignee", JOIN)
                .setFetchMode("userCreator", JOIN)
                .setFetchMode("project", JOIN)
                .add(Restrictions.eq("id",id))
                .uniqueResult();
    }

}
