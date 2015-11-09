package bugztracker.service.impl;

import bugztracker.entity.Issue;
import bugztracker.entity.IssueComment;
import bugztracker.entity.User;
import bugztracker.exception.service.IssueCommentServiceException;
import bugztracker.repository.IIssueCommentRepository;
import bugztracker.repository.IIssueRepository;
import bugztracker.repository.IUserRepository;
import bugztracker.service.IIssueCommentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by Y. Vovk on 04.11.15.
 */
@Service
public class IssueCommentService implements IIssueCommentService {

    @Autowired
    private IIssueCommentRepository issueCommentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IIssueRepository issueRepository;

    @Transactional
    @Override
    public void addComment(String text, int issueId,User sender) throws IssueCommentServiceException {
        User fullSender = userRepository.get(sender.getId());
        if (fullSender == null) {
            throw new IssueCommentServiceException("Can't find specified user!");
        }

        Issue fullIssue = issueRepository.get(issueId);
        if (fullIssue == null) {
            throw new IssueCommentServiceException("Can't find specified issue!");
        }

        IssueComment comment = new IssueComment();
        comment.setId((int) UUID.randomUUID().getMostSignificantBits());
        comment.setDate(new Timestamp(DateTime.now().getMillis()));
        comment.setSender(fullSender);
        comment.setIssueByIssueId(fullIssue);
        comment.setComment(text);
        add(comment);
    }

    @Transactional
    @Override
    public void updateComment(String comment, int issueId, int commentId) throws IssueCommentServiceException {
        IssueComment oldComment = issueCommentRepository.get(commentId);
        if(oldComment == null){
            throw new IssueCommentServiceException("Specified comment not found!");
        }
        if (oldComment.getIssueByIssueId().getId() != issueId) {
            throw new IssueCommentServiceException("Comment doesn't concern to current issue!");
        }

        oldComment.setUpdateDate(new Timestamp(DateTime.now().getMillis()));
        oldComment.setComment(comment);
        update(oldComment);
    }

    @Override
    public List<IssueComment> getAll(int issueId) {
        return issueCommentRepository.getCommentsOfIssue(issueId);
    }

    @Transactional
    @Override
    public void delete(int issueId, int commentId) throws IssueCommentServiceException {
        IssueComment comment = issueCommentRepository.get(commentId);
        if (comment == null) {
            throw new IssueCommentServiceException("Comment with specified id not found!");
        }
        if (comment.getIssueByIssueId().getId() != issueId) {
            throw new IssueCommentServiceException("Comment doesn't concern to current issue!");
        }
        issueCommentRepository.delete(comment);
    }

    @Override
    public IssueComment get(int id) {
        return issueCommentRepository.get(id);
    }

    @Override
    public List<IssueComment> getAll() {
        return issueCommentRepository.getAll();
    }

    @Transactional
    @Override
    public void add(IssueComment entity) {
        issueCommentRepository.add(entity);
    }

    @Transactional
    @Override
    public void delete(IssueComment entity) {
        issueCommentRepository.delete(entity);
    }

    @Transactional
    @Override
    public void update(IssueComment entity) {
        issueCommentRepository.update(entity);
    }
}
