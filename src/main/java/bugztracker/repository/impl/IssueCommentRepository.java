package bugztracker.repository.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.IssueComment;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IIssueCommentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Y. Vovk on 04.11.15.
 */
@Repository
public class IssueCommentRepository extends AbstractRepository<IssueComment> implements IIssueCommentRepository {

    @Autowired
    protected SessionFactory sessionFactory;

    public IssueCommentRepository() {
        super(IssueComment.class);
    }

    @Override
    public List<IssueComment> getCommentsOfIssue(Issue issue) {
        return (List<IssueComment>) sessionFactory.getCurrentSession().createCriteria(IssueComment.class)
                .add(Restrictions.eq("issue_id", issue.getId()))
                .addOrder(Order.asc("date"))
                .list();
    }
}
