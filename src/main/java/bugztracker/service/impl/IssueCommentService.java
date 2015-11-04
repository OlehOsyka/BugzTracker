package bugztracker.service.impl;

import bugztracker.entity.IssueComment;
import bugztracker.entity.User;
import bugztracker.repository.IIssueCommentRepository;
import bugztracker.repository.IIssueRepository;
import bugztracker.repository.IUserRepository;
import bugztracker.service.IIssueCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Y. Vovk on 04.11.15.
 */
@Service
@Transactional
public class IssueCommentService implements IIssueCommentService {

    @Autowired
    private IIssueCommentRepository issueCommentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IIssueRepository issueRepository;

    @Override
    public void addComment(IssueComment comment, User sender) {
        comment.setId((int) UUID.randomUUID().getMostSignificantBits());
        //comment.setDate();
        comment.setSender(userRepository.get(sender.getId()));
        comment.setIssueByIssueId(issueRepository.get(comment.getIssueByIssueId().getId()));
        add(comment);
    }

    @Override
    public void updateComment(IssueComment comment) {
        IssueComment oldComment = issueCommentRepository.get(comment.getId());
        //oldComment.setUpdateDate();
        oldComment.setComment(comment.getComment());
        update(comment);
    }

    @Override
    public IssueComment get(int id) {
        return issueCommentRepository.get(id);
    }

    @Override
    public List<IssueComment> getAll() {
        return issueCommentRepository.getAll();
    }

    @Override
    public void add(IssueComment entity) {
        issueCommentRepository.add(entity);
    }

    @Override
    public void delete(IssueComment entity) {
        issueCommentRepository.delete(entity);
    }

    @Override
    public void update(IssueComment entity) {
        issueCommentRepository.update(entity);
    }
}
