package bugztracker.repository.impl;

import bugztracker.entity.IssueAttachment;
import bugztracker.repository.AbstractRepository;
import bugztracker.repository.IIssueAttachmentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.FetchMode.JOIN;

/**
 * Created by oleg
 * Date: 02.11.15
 * Time: 10:40
 */
@Repository
public class IssueAttacmentRepository extends AbstractRepository<IssueAttachment> implements IIssueAttachmentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public IssueAttacmentRepository() {
        super(IssueAttachment.class);
    }

    @Override
    public List<IssueAttachment> getAttachments(int issueId) {
        return sessionFactory.getCurrentSession().createCriteria(IssueAttachment.class)
                .add(Restrictions.eq("issue_id", issueId))
                .list();
    }

    @Override
    public IssueAttachment getAttachment(int issueId, String fileName) {
        return (IssueAttachment) sessionFactory.getCurrentSession().createCriteria(IssueAttachment.class)
                .setFetchMode("issueByIssueId", JOIN)
                .createAlias("issueByIssueId", "issue")
                .add(Restrictions.eq("issue.id", issueId))
                .add(Restrictions.ilike("attachmentPath", "%" + fileName + "%"))
                .uniqueResult();
    }
}
