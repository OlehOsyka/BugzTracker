package bugztracker.repository.impl;

import bugztracker.entity.IssueComment;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IIssueCommentRepository;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Y. Vovk on 04.11.15.
 */
@Repository
public class IssueCommentRepository extends AbstractRepository<IssueComment> implements IIssueCommentRepository {

    public IssueCommentRepository() {
        super(IssueComment.class);
    }

    @Override
    public List<IssueComment> getCommentsOfIssue(int issueId) {
        return (List<IssueComment>) sessionFactory.getCurrentSession().createCriteria(IssueComment.class)
                .add(Restrictions.eq("issue_id", issueId))
                .addOrder(Order.asc("date"))
                .list();
    }
}
